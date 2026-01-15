package biz.ei6.eluvplages.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import biz.ei6.eluvplages.domain.Plage
import biz.ei6.eluvplages.domain.PlageType
import biz.ei6.eluvplages.presentation.PlageVM
import kotlinx.coroutines.launch

@Composable
fun EditPlageScreen(
    vm: PlageVM,
    onBack: () -> Unit,
) {
    // Champs (vides par défaut)
    var nom by remember { mutableStateOf(Plage.DEFAULT.nom) }
    var description by remember { mutableStateOf(Plage.DEFAULT.description) }
    var lieu by remember { mutableStateOf(Plage.DEFAULT.lieu) }
    var type by remember { mutableStateOf(Plage.DEFAULT.type) }

    var largeur by remember { mutableStateOf(Plage.DEFAULT.largeur.toString()) }
    var longueur by remember { mutableStateOf(Plage.DEFAULT.longueur.toString()) }
    var latitude by remember { mutableStateOf(Plage.DEFAULT.latitude.toString()) }
    var longitude by remember { mutableStateOf(Plage.DEFAULT.longitude.toString()) }

    var featuredImageUrl by remember { mutableStateOf(Plage.DEFAULT.featuredImageUrl) }
    var thumb1 by remember { mutableStateOf(Plage.DEFAULT.thumbImageUrl[0]) }
    var thumb2 by remember { mutableStateOf(Plage.DEFAULT.thumbImageUrl[1]) }
    var thumb3 by remember { mutableStateOf(Plage.DEFAULT.thumbImageUrl[2]) }

    val largeurVal = largeur.toDouble()
    val longueurVal = longueur.toDouble()
    val latitudeVal = latitude.toDouble()
    val longitudeVal = longitude.toDouble()

    val isFormValid =
        nom.isNotBlank() &&
                description.isNotBlank() &&
                lieu.isNotBlank() &&
                 largeurVal > 0.0 &&
                 longueurVal > 0.0 &&
                latitudeVal in -90.0..90.0 &&
                 longitudeVal in -180.0..180.0

    //var saving by remember { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf<String?>(null) }
   //val scope = rememberCoroutineScope()

    fun generateId(): String = "plage_${System.currentTimeMillis()}"

    fun buildThumbs(): List<String> =
        listOf(thumb1.trim(), thumb2.trim(), thumb3.trim()).filter { it.isNotBlank() }

    fun save() {
        if (!isFormValid ) return
        errorMsg = null


        val newPlage = Plage(
            id = generateId(),
            nom = nom.trim(),
            description = description.trim(),
            type = type,
            largeur = largeurVal,
            longueur = longueurVal,
            latitude = latitudeVal,
            longitude = longitudeVal,
            lieu = lieu.trim(),
            url = "https://example.com/plages/${nom.trim().lowercase().replace(" ", "-")}",
            isFavorite = false,
            thumbImageUrl = buildThumbs(),
            featuredImageUrl = featuredImageUrl?.trim()?.ifBlank { null }
        )

      //  scope.launch {
            try {
                vm.add(newPlage)

                onBack()
            } catch (t: Throwable) {

                errorMsg = t.message ?: "Erreur lors de la création"
            }
     //   }
    }

    Scaffold(

        floatingActionButton = {
            FloatingActionButton(
                onClick = { save() },

            ){
                Icon(Icons.Filled.Check, contentDescription = null) }
            }

    ) { padding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // Header maison (non experimental)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour")
                }
                Spacer(Modifier.width(8.dp))
                Text(
                    "Créer une plage",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            

            if (errorMsg != null) {
                Surface(
                    color = MaterialTheme.colorScheme.errorContainer,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text(
                        text = errorMsg!!,
                        modifier = Modifier.padding(12.dp),
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }

            Text("Infos", style = MaterialTheme.typography.titleLarge)

            OutlinedTextField(
                value = nom,
                onValueChange = { nom = it },
                label = { Text("Nom") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            PlageTypeSelector(
                value = type,
                onValueChange = { type = it },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = lieu,
                onValueChange = { lieu = it },
                label = { Text("Lieu") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 4
            )

            HorizontalDivider()

            Text("Dimensions", style = MaterialTheme.typography.titleLarge)

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = largeur,
                    onValueChange = { largeur = it },
                    label = { Text("Largeur (m)") },
                    isError = largeur.isNotBlank() && ( largeurVal <= 0.0),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = longueur,
                    onValueChange = { longueur = it },
                    label = { Text("Longueur (m)") },
                    isError = longueur.isNotBlank() && ( longueurVal <= 0.0),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
            }

            HorizontalDivider()

            Text("Coordonnées", style = MaterialTheme.typography.titleLarge)

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = latitude,
                    onValueChange = { latitude = it },
                    label = { Text("Latitude") },
                    isError = latitude.isNotBlank() && ( latitudeVal !in -90.0..90.0),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = longitude,
                    onValueChange = { longitude = it },
                    label = { Text("Longitude") },
                    isError = longitude.isNotBlank() && ( longitudeVal !in -180.0..180.0),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.weight(1f)
                )
            }

            HorizontalDivider()

            Text("Images", style = MaterialTheme.typography.titleLarge)

            OutlinedTextField(
                value = featuredImageUrl ?: "aucune",
                onValueChange = { featuredImageUrl = it },
                label = { Text("Featured image URL (optionnel)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = thumb1,
                onValueChange = { thumb1 = it },
                label = { Text("Thumbnail 1 (optionnel)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = thumb2,
                onValueChange = { thumb2 = it },
                label = { Text("Thumbnail 2 (optionnel)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = thumb3,
                onValueChange = { thumb3 = it },
                label = { Text("Thumbnail 3 (optionnel)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            AssistChip(
                onClick = { /* no-op */ },
                enabled = false,
                label = { Text(if (isFormValid) "Prêt à créer" else "Champs manquants/invalides") }
            )

            Spacer(Modifier.height(80.dp)) // pour laisser de l’air au FAB
        }
    }
}

@Composable
private fun PlageTypeSelector(
    value: PlageType,
    onValueChange: (PlageType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text("Type", style = MaterialTheme.typography.labelLarge)
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            PlageType.entries.forEach { t ->
                FilterChip(
                    selected = value == t,
                    onClick = { onValueChange(t) },
                    label = { Text(t.name) }
                )
            }
        }
    }
}
