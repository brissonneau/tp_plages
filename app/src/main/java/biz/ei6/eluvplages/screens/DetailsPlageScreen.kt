package biz.ei6.eluvplages.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.NearMe
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import biz.ei6.eluvplages.domain.Plage
import biz.ei6.eluvplages.presentation.PlageVM
import biz.ei6.eluvplages.ui.theme.PlagesTheme
import coil.compose.AsyncImage


@Composable
fun DetailsPlageScreen(
    vm: PlageVM = viewModel(),
    current: Plage,
    onBack: () -> Unit = {},
    onEdit: () -> Unit = {},
    onMore: () -> Unit = {},
) {

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { vm.toggleFavorite(current.id) },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.surfaceVariant,
                shape = RoundedCornerShape(16.dp),
                text = { Text("Favori", fontWeight = FontWeight.Bold) },
                icon = { if(current.isFavorite)
                    Icon(Icons.Filled.Favorite, contentDescription = null)
                else
                    Icon(Icons.Outlined.FavoriteBorder, contentDescription = null)
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {

            /* ───────────── Top App Bar ───────────── */

            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Retour",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    Row {
                        IconButton(onClick = onEdit) {
                            Icon(Icons.Outlined.Edit, contentDescription = "Éditer",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                        IconButton(onClick = onMore) {
                            Icon(Icons.Outlined.MoreVert, contentDescription = "Plus",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }

                Text(
                    current.nom,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            /* ───────────── Hero Image ───────────── */

            Box(
                modifier = Modifier

                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.surface)
                    .aspectRatio(16f / 9f)
                    .clip(RoundedCornerShape(14.dp))
            ) {
                AsyncImage(
                    model = current.featuredImageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Surface(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.9f),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp)
                ) {
                    Text(
                        text = current.type.toString(),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }

            /* ───────────── Info Cards ───────────── */

            InfoCard(
                icon = Icons.Outlined.CalendarMonth,
                title = "Date et Heure",
                lines = listOf(current.largeur.toString() , current.longueur.toString()),
                trailing = {

                },
                surface = MaterialTheme.colorScheme.surface,
                primary = MaterialTheme.colorScheme.primary,
                muted = MaterialTheme.colorScheme.onSurfaceVariant
            )

            InfoCard(
                icon = Icons.Outlined.LocationOn,
                title = "Lieu",
                lines = listOf(current.lieu),
                trailing = {
                    IconButton(onClick = {  }) {
                        Icon(Icons.Outlined.Map, contentDescription = "Carte",
                            tint = MaterialTheme.colorScheme.primary)
                    }
                },
                surface = MaterialTheme.colorScheme.surface,
                primary = MaterialTheme.colorScheme.primary,
                muted = MaterialTheme.colorScheme.onSurfaceVariant
            )

            /* ───────────── Map Preview ───────────── */

            Box(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(130.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                AsyncImage(
                    model = "https://placehold.co/600x400?text=Map+Viroflay",

                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                ) {
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.85f)
                    ) {
                        Icon(
                            Icons.Outlined.NearMe,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.surface,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }



            HorizontalDivider(Modifier.padding(16.dp), color = Color.DarkGray)

            /* ───────────── Description ───────────── */

            Column(Modifier.padding(horizontal = 16.dp)) {
                Text(
                    "À propos de la plage",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text=current.description,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp)
                )


            }

            /* ───────────── Galerie ───────────── */

            Column(Modifier.padding(top = 16.dp)) {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Galerie", color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.titleLarge)

                }

                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    for(url in current.thumbImageUrl) {
                        AsyncImage(
                            model = url,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(14.dp))
                        )
                    }
                }
            }

            Spacer(Modifier.height(100.dp))
        }
    }
}

/* ───────────── Reusable Components ───────────── */

@Composable
private fun InfoCard(
    icon: ImageVector,
    title: String,
    lines: List<String>,
    trailing: @Composable () -> Unit,
    surface: Color,
    primary: Color,
    muted: Color
) {
    Surface(
        color = surface,
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = primary.copy(alpha = 0.1f)
            ) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = primary,
                    modifier = Modifier.padding(10.dp)
                )
            }

            Spacer(Modifier.width(12.dp))

            Column(Modifier.weight(1f)) {
                Text(title, color = Color.White, fontWeight = FontWeight.SemiBold)
                lines.forEach {
                    Text(it, color = muted, style = MaterialTheme.typography.bodySmall)
                }
            }

            trailing()
        }
    }
}



@Composable
@Preview(showBackground = true)
fun JudoEventDetailScreenPreview() {
    PlagesTheme(darkTheme = true) {
        DetailsPlageScreen(current = Plage.DEFAULT)
    }
}