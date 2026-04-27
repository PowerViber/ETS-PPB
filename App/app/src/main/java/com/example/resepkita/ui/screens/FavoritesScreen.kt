package com.example.resepkita.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.example.resepkita.ui.theme.md_theme_light_outline
import com.example.resepkita.ui.theme.md_theme_light_outlineVariant
import com.example.resepkita.ui.theme.surfaceContainerHigh
import com.example.resepkita.ui.theme.surfaceContainerHighest
import com.example.resepkita.ui.theme.surfaceContainerLow
import com.example.resepkita.ui.theme.surfaceContainerLowest

private data class FavoriteRecipe(
    val title: String,
    val region: String,
    val imageUrl: String,
    val time: String,
    val rating: String,
    val note: String
)

private val favoriteRecipes = listOf(
    FavoriteRecipe(
        title = "Rendang Minang Asli",
        region = "SUMATERA BARAT",
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDqquPhp3-g1oe62vwe2d7V3E_0p7ag5glsmI4D2abNENlncVrQMeB2NNk8l7qpl5pQ3PUgOtiCstW5iAx5G_S4N_JDiZzgHw4_b5wqM9bGQMHIh8heWusDrYHVWMiHBgWi6JJzzAlYvKYcdyGewgxcMtEpvgB6XUmUyL0dVo-4QtD3X98O8JExvDuvsivQyNUOdVA5xcGD3Feyc6nB-z4JgY41DmaFqDw_ANugpysFEaaJ2-RAkioz5YTpOsL_wp2dxcvm5ngGDh02",
        time = "120 MENIT",
        rating = "4.9",
        note = "Disimpan untuk akhir pekan"
    ),
    FavoriteRecipe(
        title = "Sate Madura",
        region = "JAWA TIMUR",
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDtPQCbh1VB-bvpoDjBt36tGDo23hU7poaqzaHNX-VmgGYca1sSij6-gp_9_hswYQfrKrbsJVyh8jabMZu_oAVfjVKKpaWCx2UDljGL2hfyEgusytZaIzUgKDDY51r6JrB0Sa_jqLCOGgcZYdmEw_qeaPo87O_DTuQkqfkaQN5yPC5PjdCVpS5r7mtqV2Esyxif6hutnRpxfCr6llV0uqUetiM0ws6fyF_l535pbpu1yVAyh-kKCFxwL1gp9iwkMYmVT8HOs1RWMFVC",
        time = "45 MENIT",
        rating = "4.7",
        note = "Bumbu kacang favorit"
    ),
    FavoriteRecipe(
        title = "Gado-Gado Siram",
        region = "BETAWI",
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBLum8-cNLnrTTci5mEY-uM3PZRx2_p9tN-7YOBfITkFwT29jtRwOKww_qsJJNvag_FQzM6_mqpzy1NW2gmzXZ45CnJdphZhBOKGLF5ksVakJ4iXeXGdabR31lCBnj3XcBgw1fJ1Hf4nDEgK380zbdVOgNJ28UEgkXmEABJeLSiEye5tgqKkD06qWR07Q5cMwfrYdvO21KPd0UH_rtuOOV2tn5D_cUjxc5pZZeosdDlwKqujR2VsLD7LcLv7txT525BLblJ6aIXLK10",
        time = "25 MENIT",
        rating = "4.8",
        note = "Cepat untuk makan siang"
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(onNavigateToDetail: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Nusantara",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Black,
                            color = Color(0xFF7c2d12)
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
                )
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
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    text = "DAPUR TERSIMPAN",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.secondary,
                        letterSpacing = 2.sp
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Koleksi",
                    style = MaterialTheme.typography.displayMedium.copy(fontStyle = FontStyle.Italic)
                )
                Text(
                    text = "Rasa Favorit",
                    style = MaterialTheme.typography.displayMedium.copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontStyle = FontStyle.Italic
                    )
                )
                Spacer(modifier = Modifier.height(24.dp))
                FavoritesSummary()
            }

            LazyRow(
                contentPadding = PaddingValues(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item { CategoryChip("SEMUA", isSelected = true) }
                item { CategoryChip("MAKAN MALAM", isSelected = false) }
                item { CategoryChip("CEPAT", isSelected = false) }
                item { CategoryChip("WARISAN", isSelected = false) }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier.padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                favoriteRecipes.forEach { recipe ->
                    FavoriteRecipeCard(recipe = recipe, onClick = onNavigateToDetail)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Surface(
                color = surfaceContainerLow,
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "CATATAN RASA",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        letterSpacing = 2.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Resep favoritmu condong ke hidangan berbumbu kuat. Coba simpan satu menu sayur segar untuk menyeimbangkan meja makan.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(120.dp))
        }
    }
}

@Composable
private fun FavoritesSummary() {
    Surface(
        color = surfaceContainerHighest,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SummaryMetric(value = "12", label = "Resep")
            SummaryMetric(value = "4", label = "Daerah")
            SummaryMetric(value = "3", label = "Rencana")
        }
    }
}

@Composable
private fun SummaryMetric(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.primary
            )
        )
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            letterSpacing = 1.sp
        )
    }
}

@Composable
private fun FavoriteRecipeCard(recipe: FavoriteRecipe, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = surfaceContainerLowest),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = recipe.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(116.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(18.dp))
                    .background(surfaceContainerHigh)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = recipe.region,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.headlineSmall.copy(fontStyle = FontStyle.Italic)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(recipe.rating, style = MaterialTheme.typography.labelMedium)
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(modifier = Modifier.size(4.dp).background(md_theme_light_outlineVariant, CircleShape))
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(Icons.Default.Schedule, contentDescription = null, tint = md_theme_light_outline, modifier = Modifier.size(15.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(recipe.time, style = MaterialTheme.typography.labelSmall, color = md_theme_light_outline)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = recipe.note,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                Icons.Default.Favorite,
                contentDescription = "Favorited",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(20.dp)
            )
        }
    }
}
