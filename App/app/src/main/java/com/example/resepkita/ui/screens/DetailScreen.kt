package com.example.resepkita.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.resepkita.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(onNavigateBack: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            
            // Hero Image
            Box(modifier = Modifier.fillMaxWidth().height(400.dp)) {
                AsyncImage(
                    model = "https://lh3.googleusercontent.com/aida-public/AB6AXuAACknOzwT_qQMaRIbWoHapLl7cZGk18kUaTObwSFRdx00nNSusVefrF6hJVOop4gKeDGrcJ_EYwUkfcU4yrYSxUk1dtAoSNPfCgJEgcC59MZttb1McBsre3DlOPccMATk_DGsgnwoArI8aaYin_q6iLQTm5trzglGyzPQrQ64uHh-cJnvdEUg7kGtqNefjFMWeGOxPO7Bg2yL-1BFAkNywnc0B4SVKQwVAe1UA5dvFSGX8RIqBYHieb9JRWuoQndMl4wL0haq1m59m",
                    contentDescription = "Rendang Daging Sapi",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, MaterialTheme.colorScheme.background),
                                startY = 300f
                            )
                        )
                )
            }

            // Content Body
            Column(modifier = Modifier.padding(horizontal = 24.dp).offset(y = (-40).dp)) {
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(
                            text = "MINANGKABAU HERITAGE",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary,
                            letterSpacing = 2.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Rendang Daging Sapi Padang",
                            style = MaterialTheme.typography.displayMedium.copy(fontStyle = FontStyle.Italic)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "A slow-cooked masterpiece of beef braised in coconut milk and a rich spice paste until the liquid evaporates and the meat turns dark, tender, and intensely flavorful.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        
                        Spacer(modifier = Modifier.height(24.dp))

                        // Stats Grid
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            StatCard("Prep Time", "4 Hours")
                            StatCard("Servings", "6 Portions")
                            StatCard("Complexity", "Artisan")
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        // Tabs Mock
                        Row {
                            Column {
                                Text(
                                    "Bahan-bahan",
                                    style = MaterialTheme.typography.headlineSmall.copy(fontStyle = FontStyle.Italic),
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Box(modifier = Modifier.height(4.dp).width(120.dp).background(MaterialTheme.colorScheme.primary, CircleShape))
                            }
                            Spacer(modifier = Modifier.width(32.dp))
                            Text(
                                "Cara Pembuatan",
                                style = MaterialTheme.typography.headlineSmall.copy(fontStyle = FontStyle.Italic),
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        // The Base Ingredients
                        Text("THE BASE", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f), letterSpacing = 2.sp)
                        Spacer(modifier = Modifier.height(16.dp))
                        IngredientItem("1kg Beef Shank, cut into cubes")
                        IngredientItem("3 Liters thick coconut milk")
                        IngredientItem("4 stalks Lemongrass, bruised")

                        Spacer(modifier = Modifier.height(24.dp))

                        // The Spiced Paste
                        Text("THE SPICED PASTE (BUMBU)", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f), letterSpacing = 2.sp)
                        Spacer(modifier = Modifier.height(16.dp))
                        IngredientItem("100g Shallots & 50g Garlic")
                        IngredientItem("250g Red Chilies (mix of curly and bird's eye)")
                        IngredientItem("50g Galangal & 20g Ginger")

                        Spacer(modifier = Modifier.height(40.dp))

                        // Instructions
                        InstructionStep("01", "The Infusion Begins", "In a large heavy-bottomed pot or wok, combine the coconut milk, spice paste, lemongrass...")
                        Spacer(modifier = Modifier.height(24.dp))
                        InstructionStep("02", "Slow Reduction", "Add the beef cubes. Reduce heat to low and simmer, stirring occasionally. This is the patience phase...")
                        
                        Spacer(modifier = Modifier.height(120.dp))
                    }
                }
            }
        }

        // Custom Top App Bar overlay
        TopAppBar(
            title = {
                Text(
                    text = "Nusantara",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.Black,
                        color = Color(0xFF7c2d12) // text-orange-900
                    )
                )
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color(0xFF9a3412))
                }
            },
            actions = {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.Favorite, contentDescription = "Favorite", tint = Color(0xFF9a3412))
                }
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.Share, contentDescription = "Share", tint = Color(0xFF9a3412))
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
            ),
            modifier = Modifier.align(Alignment.TopCenter)
        )

        // FAB
        ExtendedFloatingActionButton(
            onClick = { /* Start cooking */ },
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 32.dp),
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            elevation = FloatingActionButtonDefaults.elevation(8.dp)
        ) {
            Text("MULAI MEMASAK", style = MaterialTheme.typography.labelMedium, letterSpacing = 1.sp)
        }
    }
}

@Composable
fun StatCard(label: String, value: String) {
    Surface(
        color = surfaceContainerLow,
        shape = CircleShape
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun IngredientItem(text: String) {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        color = surfaceContainerLowest,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = false, onCheckedChange = { }, colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary))
            Spacer(modifier = Modifier.width(16.dp))
            Text(text, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun InstructionStep(number: String, title: String, desc: String) {
    Row(verticalAlignment = Alignment.Top) {
        Surface(
            shape = CircleShape,
            color = if (number == "01") Color(0xFFffdbd1) else surfaceContainerHighest,
            modifier = Modifier.size(56.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = number,
                    style = MaterialTheme.typography.headlineSmall.copy(fontStyle = FontStyle.Italic, fontWeight = FontWeight.Black),
                    color = if (number == "01") Color(0xFF872000) else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        Spacer(modifier = Modifier.width(24.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.headlineSmall.copy(fontStyle = FontStyle.Italic))
            Spacer(modifier = Modifier.height(8.dp))
            Text(desc, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
