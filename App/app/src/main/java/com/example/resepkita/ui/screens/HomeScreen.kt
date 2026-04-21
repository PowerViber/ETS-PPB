package com.example.resepkita.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun HomeScreen(
    onNavigateToDetail: () -> Unit,
    onNavigateToSearch: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Nusantara",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Black,
                            color = Color(0xFF7c2d12) // text-orange-900 equivalent
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Handle Menu */ }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu", tint = Color(0xFF9a3412))
                    }
                },
                actions = {
                    AsyncImage(
                        model = "https://lh3.googleusercontent.com/aida-public/AB6AXuB-n4KjNDymhKmMVkoYGIm9qHfq1MyC0HQTfnjkpTurfF6aFsW-0eivLOXgsSU4zkUQJltDoTARYg2PWAsu3tuGFfeVNOI_ioq7oAwoIMYz2y6nLFZe-u2VRuPHdf2o_Dx5FKogCvuaYp8BJtE2JpFmBM2c_ie1AUAs8zcoKj6ZlXboCikqb81RHBeymmxST2AIAKr8X3lyoBw8D5LGyPBNnzhk_tqkvq59-Im1-GEyek99qjm4TUc1U3pySo5Y9sZ3c-fSHn2DnLgF",
                        contentDescription = "Profile",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(surfaceContainerHigh)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
                ),
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            // Hero Section
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    text = "DIGITAL HERITAGE",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.secondary,
                        letterSpacing = 2.sp
                    ),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Simfoni",
                    style = MaterialTheme.typography.displayLarge.copy(fontStyle = FontStyle.Italic)
                )
                Row {
                    Text(
                        text = "Rasa ",
                        style = MaterialTheme.typography.displayLarge.copy(fontStyle = FontStyle.Italic)
                    )
                    Text(
                        text = "Nusantara",
                        style = MaterialTheme.typography.displayLarge.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontStyle = FontStyle.Italic
                        )
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Search Bar Placeholder
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = surfaceContainerHighest,
                    modifier = Modifier.fillMaxWidth().clickable { onNavigateToSearch() }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = md_theme_light_outline)
                        Spacer(modifier = Modifier.width(12.dp))
                        Text("Cari resep hari ini...", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }
            }

            // Categories
            LazyRow(
                contentPadding = PaddingValues(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item { CategoryChip("POPULER", isSelected = true) }
                item { CategoryChip("TRADISIONAL", isSelected = false) }
                item { CategoryChip("MODERN", isSelected = false) }
                item { CategoryChip("VEGETARIAN", isSelected = false) }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Featured Recipe Card
            FeaturedRecipeCard(onNavigateToDetail)

            Spacer(modifier = Modifier.height(24.dp))

            // Secondary Recipes List
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                SecondaryRecipeCard(
                    title = "Gado-Gado Siram",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBLum8-cNLnrTTci5mEY-uM3PZRx2_p9tN-7YOBfITkFwT29jtRwOKww_qsJJNvag_FQzM6_mqpzy1NW2gmzXZ45CnJdphZhBOKGLF5ksVakJ4iXeXGdabR31lCBnj3XcBgw1fJ1Hf4nDEgK380zbdVOgNJ28UEgkXmEABJeLSiEye5tgqKkD06qWR07Q5cMwfrYdvO21KPd0UH_rtuOOV2tn5D_cUjxc5pZZeosdDlwKqujR2VsLD7LcLv7txT525BLblJ6aIXLK10",
                    rating = "4.8",
                    time = "25 MENIT",
                    difficulty = "PEMULA"
                )
                Spacer(modifier = Modifier.height(16.dp))
                SecondaryRecipeCard(
                    title = "Sate Madura",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDtPQCbh1VB-bvpoDjBt36tGDo23hU7poaqzaHNX-VmgGYca1sSij6-gp_9_hswYQfrKrbsJVyh8jabMZu_oAVfjVKKpaWCx2UDljGL2hfyEgusytZaIzUgKDDY51r6JrB0Sa_jqLCOGgcZYdmEw_qeaPo87O_DTuQkqfkaQN5yPC5PjdCVpS5r7mtqV2Esyxif6hutnRpxfCr6llV0uqUetiM0ws6fyF_l535pbpu1yVAyh-kKCFxwL1gp9iwkMYmVT8HOs1RWMFVC",
                    rating = "4.7",
                    time = "45 MENIT",
                    difficulty = "MENENGAH"
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
            
            // Bento Section
            Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                Surface(
                    color = surfaceContainerLow,
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(modifier = Modifier.padding(start = 4.dp).background(MaterialTheme.colorScheme.primary)) {
                        Column(modifier = Modifier.background(surfaceContainerLow).padding(24.dp).fillMaxWidth()) {
                            Text("QUICK TIPS", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary, letterSpacing = 2.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Rahasia Sambal Bajak yang Gurih", style = MaterialTheme.typography.headlineSmall.copy(fontStyle = FontStyle.Italic))
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Gunakan terasi yang sudah dibakar untuk aroma yang lebih kuat...", style = MaterialTheme.typography.bodyMedium)
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("BACA TIPS", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
                                Icon(Icons.Default.ArrowForward, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(100.dp)) // Bottom Nav space
        }
    }
}

@Composable
fun CategoryChip(text: String, isSelected: Boolean) {
    Surface(
        shape = CircleShape,
        color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else surfaceContainerHigh,
        modifier = Modifier.clickable { /* Handle Chip Click */ }
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
        )
    }
}

@Composable
fun FeaturedRecipeCard(onNavigateToDetail: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .clickable { onNavigateToDetail() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = surfaceContainerLow),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                AsyncImage(
                    model = "https://lh3.googleusercontent.com/aida-public/AB6AXuDqquPhp3-g1oe62vwe2d7V3E_0p7ag5glsmI4D2abNENlncVrQMeB2NNk8l7qpl5pQ3PUgOtiCstW5iAx5G_S4N_JDiZzgHw4_b5wqM9bGQMHIh8heWusDrYHVWMiHBgWi6JJzzAlYvKYcdyGewgxcMtEpvgB6XUmUyL0dVo-4QtD3X98O8JExvDuvsivQyNUOdVA5xcGD3Feyc6nB-z4JgY41DmaFqDw_ANugpysFEaaJ2-RAkioz5YTpOsL_wp2dxcvm5ngGDh02",
                    contentDescription = "Rendang Daging",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                // Terpopuler Badge
                Surface(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = CircleShape,
                    modifier = Modifier.padding(16.dp).align(Alignment.TopStart)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Using LocalFireDepartment equivalent
                        Text("TERPOPULER", style = MaterialTheme.typography.labelSmall, color = Color.White)
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-40).dp)
                    .padding(horizontal = 16.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White.copy(alpha = 0.95f),
                    shadowElevation = 8.dp,
                    tonalElevation = 4.dp
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Row(modifier = Modifier.padding(bottom = 8.dp)) {
                            Icon(Icons.Default.Star, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("4.9", style = MaterialTheme.typography.labelMedium)
                            Spacer(modifier = Modifier.width(16.dp))
                            Text("120 Menit", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.secondary)
                        }
                        Text(
                            text = "Rendang Minang Asli",
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Black)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Warisan leluhur Minangkabau dengan bumbu rempah melimpah yang dimasak perlahan hingga meresap sempurna.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = onNavigateToDetail,
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Text("LIHAT RESEP", style = MaterialTheme.typography.labelSmall, letterSpacing = 1.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SecondaryRecipeCard(
    title: String,
    imageUrl: String,
    rating: String,
    time: String,
    difficulty: String
) {
    Column {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = surfaceContainerLow,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4f / 3f)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(title, style = MaterialTheme.typography.headlineSmall)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Star, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                Text(rating, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(time, style = MaterialTheme.typography.labelSmall, letterSpacing = 1.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.size(4.dp).background(md_theme_light_outlineVariant, CircleShape))
            Spacer(modifier = Modifier.width(8.dp))
            Text(difficulty, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.secondary, letterSpacing = 1.sp)
        }
    }
}
