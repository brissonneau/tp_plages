package biz.ei6.eluvplages.screens

import android.Manifest
import android.view.ViewGroup
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

import biz.ei6.eluvplages.domain.Plage
import kotlinx.coroutines.flow.StateFlow
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapPlageScreen(
    eventsFlow: StateFlow<List<Plage>>,
    onBack: () -> Unit
) {
    BackHandler(enabled = true) {
        onBack()
    }
    Scaffold { padding ->
        Box(Modifier.fillMaxSize().padding(padding)) {
            // La map
            MapContent(
                eventsFlow = eventsFlow,
                modifier = Modifier.fillMaxSize()
            )

            // Top bar au-dessus de la map (et donc cliquable)
            TopAppBar(
                title = { Text("Carte des événements") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Retour")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(10f) // 👈 CRUCIAL
            )
        }
    }
}

@Composable
fun MapContent(
    eventsFlow: StateFlow<List<Plage>>, // tu passes repo.events ou vm.events
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lifecycleOwner = androidx.lifecycle.compose.LocalLifecycleOwner.current

    // osmdroid: user-agent
    LaunchedEffect(Unit) {
        Configuration.getInstance().userAgentValue = context.packageName
    }

    // permission localisation
    var hasLocationPermission by remember { mutableStateOf(false) }
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasLocationPermission = granted
    }

    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    val events by eventsFlow.collectAsState()

    // MapView stable
    val mapView = remember {
        MapView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setTileSource(TileSourceFactory.MAPNIK)
            setMultiTouchControls(true)
            zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)
            controller.setZoom(3.0) // zoom “monde” en attendant la position
            isTilesScaledToDpi = true
        }
    }

    // Overlay "ma position"
    val myLocationOverlay = remember {
        MyLocationNewOverlay(GpsMyLocationProvider(context), mapView).apply {
            enableMyLocation()
            // enableFollowLocation() // optionnel: suivre la position en continu
        }
    }

    // Ajouter overlays une seule fois
    LaunchedEffect(Unit) {
        if (!mapView.overlays.contains(myLocationOverlay)) {
            mapView.overlays.add(myLocationOverlay)
        }
    }

    // Centrage sur position actuelle dès que possible
    LaunchedEffect(hasLocationPermission) {
        if (hasLocationPermission) {
            myLocationOverlay.enableMyLocation()
            myLocationOverlay.runOnFirstFix {
                val loc = myLocationOverlay.myLocation
                if (loc != null) {
                    mapView.post {
                        mapView.controller.setZoom(5.5)
                        mapView.controller.setCenter(GeoPoint(loc.latitude, loc.longitude))
                    }
                }
            }
        } else {
            // si pas de permission: centre monde (déjà fait), ou tu peux centrer sur Paris
            myLocationOverlay.disableMyLocation()
        }
    }

    // Markers: on garde une liste pour pouvoir nettoyer/remettre facilement
    val eventMarkers = remember { mutableStateListOf<Marker>() }

    // Rebuild markers quand la liste change
    LaunchedEffect(events) {
        // retire anciens markers
        eventMarkers.forEach { mapView.overlays.remove(it) }
        eventMarkers.clear()

        // ajoute nouveaux markers
        val markers = events.mapNotNull { e ->
            val lat = e.latitude
            val lon = e.longitude
            // ignore les positions non renseignées
            if (lat == 0.0 && lon == 0.0) return@mapNotNull null

            Marker(mapView).apply {
                position = GeoPoint(lat, lon)
                setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                title = e.nom
                subDescription = e.lieu.takeIf { it.isNotBlank() } ?: e.type.toString()
                // click -> tu peux déclencher une navigation / ouvrir une fiche
                setOnMarkerClickListener { m, _ ->
                    m.showInfoWindow()
                    true
                }
            }
        }

        markers.forEach { mapView.overlays.add(it) }
        eventMarkers.addAll(markers)
        mapView.invalidate()
    }

    // Lifecycle forwarding
    DisposableEffect(lifecycleOwner) {
        val obs = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(obs)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(obs)
            myLocationOverlay.disableMyLocation()
            mapView.onPause()
            mapView.onDetach()
        }
    }

    AndroidView(
        factory = { mapView.apply {
            isFocusable = false
            isFocusableInTouchMode = false
        } },
        modifier = modifier.fillMaxSize()
    )
}