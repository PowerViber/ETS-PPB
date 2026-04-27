package com.example.resepkita.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.resepkita.data.Recipe
import com.example.resepkita.data.RecipeRepository
import com.example.resepkita.ui.components.ResepKitaTopBar
import com.example.resepkita.ui.theme.md_theme_light_onTertiaryContainer
import com.example.resepkita.ui.theme.md_theme_light_outline
import com.example.resepkita.ui.theme.md_theme_light_tertiaryContainer
import com.example.resepkita.ui.theme.surfaceContainerHighest
import com.example.resepkita.ui.theme.surfaceContainerLow
import com.example.resepkita.ui.theme.surfaceContainerLowest

@Composable
fun SearchScreen(onNavigateToDetail: (String) -> Unit) {
    var query by remember { mutableStateOf("") }
    val results = RecipeRepository.search(query)

    Scaffold(
        topBar = {
            ResepKitaTopBar()
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            contentPadding = PaddingValues(bottom = 112.dp)
        ) {
            item {
                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    placeholder = { Text("Cari nama resep atau bahan...", color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = md_theme_light_outline) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                        unfocusedBorderColor = Color.Transparent,
                        focusedContainerColor = surfaceContainerHighest,
                        unfocusedContainerColor = surfaceContainerHighest
                    )
                )

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Bahan Populer",
                        style = MaterialTheme.typography.headlineSmall.copy(fontStyle = FontStyle.Italic)
                    )
                    Text(
                        text = "PILIH CEPAT",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.secondary,
                        letterSpacing = 1.sp
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    PopularIngredient("Ayam", RecipeRepository.findById("ayam-betutu").imageUrl, Modifier.weight(1f)) { query = "ayam" }
                    PopularIngredient("Daging", RecipeRepository.findById("rendang-minang").imageUrl, Modifier.weight(1f)) { query = "daging" }
                    PopularIngredient("Tempe", RecipeRepository.findById("gado-gado-siram").imageUrl, Modifier.weight(1f)) { query = "tempe" }
                }

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = if (query.isBlank()) "Semua Resep" else "Hasil Pencarian",
                    style = MaterialTheme.typography.headlineSmall.copy(fontStyle = FontStyle.Italic)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            if (results.isEmpty()) {
                item {
                    EmptySearchState(query = query)
                }
            } else {
                items(results, key = { it.id }) { recipe ->
                    SearchResultItem(recipe = recipe, onClick = { onNavigateToDetail(recipe.id) })
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            item {
                Spacer(modifier = Modifier.height(28.dp))
                FeatureSpotlight()
            }
        }
    }
}

@Composable
fun PopularIngredient(name: String, imageUrl: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Surface(
        color = surfaceContainerLow,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.clickable { onClick() }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                name,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun SearchResultItem(recipe: Recipe, onClick: () -> Unit) {
    Surface(
        color = surfaceContainerLowest,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                AsyncImage(
                    model = recipe.imageUrl,
                    contentDescription = recipe.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(56.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(recipe.name, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
                    Text(
                        "${recipe.region} - ${recipe.time} - ${recipe.difficulty}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.secondary,
                        letterSpacing = 1.sp
                    )
                }
            }
            Icon(Icons.Default.ArrowForwardIos, contentDescription = null, tint = md_theme_light_outline, modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
private fun EmptySearchState(query: String) {
    Surface(
        color = surfaceContainerLow,
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "Belum Ada Resep",
                style = MaterialTheme.typography.headlineSmall.copy(fontStyle = FontStyle.Italic)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Tidak ada resep yang cocok dengan \"$query\". Coba cari nama masakan atau bahan seperti ayam, daging, atau tempe.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun FeatureSpotlight() {
    Surface(
        color = md_theme_light_tertiaryContainer,
        shape = RoundedCornerShape(32.dp),
        modifier = Modifier.fillMaxWidth().height(200.dp)
    ) {
        Box {
            Column(modifier = Modifier.padding(32.dp).fillMaxWidth(0.68f)) {
                Text(
                    text = "REKOMENDASI CHEF",
                    style = MaterialTheme.typography.labelSmall,
                    color = md_theme_light_onTertiaryContainer.copy(alpha = 0.8f),
                    letterSpacing = 2.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Eksplorasi Rempah Pilihan Musim Hujan",
                    style = MaterialTheme.typography.headlineSmall.copy(fontStyle = FontStyle.Italic, color = md_theme_light_onTertiaryContainer)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Text("BACA JURNAL", style = MaterialTheme.typography.labelSmall, letterSpacing = 1.sp)
                }
            }
        }
    }
}
