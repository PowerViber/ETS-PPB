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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.resepkita.ui.theme.md_theme_light_outline
import com.example.resepkita.ui.theme.md_theme_light_outlineVariant
import com.example.resepkita.ui.theme.surfaceContainerHigh
import com.example.resepkita.ui.theme.surfaceContainerHighest
import com.example.resepkita.ui.theme.surfaceContainerLow
import com.example.resepkita.ui.theme.surfaceContainerLowest

@Composable
fun FavoritesScreen(
    favoriteRecipeIds: List<String>,
    onNavigateToDetail: (String) -> Unit
) {
    val favoriteRecipes = RecipeRepository.recipes.filter { favoriteRecipeIds.contains(it.id) }
    val categories = listOf("Semua") + favoriteRecipes.map { it.category }.distinct()
    var selectedCategory by rememberSaveable { mutableStateOf("Semua") }
    val visibleRecipes = if (selectedCategory == "Semua") {
        favoriteRecipes
    } else {
        favoriteRecipes.filter { it.category == selectedCategory }
    }

    Scaffold(
        topBar = {
            ResepKitaTopBar()
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = 112.dp)
        ) {
            item {
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
                    FavoritesSummary(favoriteRecipes)
                }
            }

            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(categories) { category ->
                        CategoryChip(
                            text = category.uppercase(),
                            isSelected = selectedCategory == category,
                            onClick = { selectedCategory = category }
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(32.dp)) }

            items(visibleRecipes, key = { it.id }) { recipe ->
                Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                    FavoriteRecipeCard(recipe = recipe, onClick = { onNavigateToDetail(recipe.id) })
                    Spacer(modifier = Modifier.height(18.dp))
                }
            }

            item {
                Spacer(modifier = Modifier.height(14.dp))
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
            }
        }
    }
}

@Composable
private fun FavoritesSummary(favoriteRecipes: List<Recipe>) {
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
            SummaryMetric(value = favoriteRecipes.size.toString(), label = "Resep")
            SummaryMetric(value = favoriteRecipes.map { it.region }.distinct().size.toString(), label = "Daerah")
            SummaryMetric(value = favoriteRecipes.count { it.difficulty != "Pemula" }.toString(), label = "Rencana")
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
private fun FavoriteRecipeCard(recipe: Recipe, onClick: () -> Unit) {
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
                contentDescription = recipe.name,
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
                    text = recipe.region.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.secondary,
                    letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = recipe.name,
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
                    Text(recipe.time.uppercase(), style = MaterialTheme.typography.labelSmall, color = md_theme_light_outline)
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
