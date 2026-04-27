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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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

@Composable
fun HomeScreen(
    onNavigateToDetail: (String) -> Unit,
    onNavigateToSearch: () -> Unit
) {
    val recipes = RecipeRepository.recipes
    val featuredRecipe = recipes.first()
    var expandedRecipeId by rememberSaveable { mutableStateOf<String?>(null) }

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

                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = surfaceContainerHighest,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onNavigateToSearch() }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Icon(Icons.Default.Search, contentDescription = null, tint = md_theme_light_outline)
                            Spacer(modifier = Modifier.width(12.dp))
                            Text("Cari resep hari ini...", color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }

            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item { CategoryChip("POPULER", isSelected = true) }
                    item { CategoryChip("TRADISIONAL", isSelected = false) }
                    item { CategoryChip("MODERN", isSelected = false) }
                    item { CategoryChip("VEGETARIAN", isSelected = false) }
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
                FeaturedRecipeCard(
                    recipe = featuredRecipe,
                    isExpanded = expandedRecipeId == featuredRecipe.id,
                    onToggle = {
                        expandedRecipeId = if (expandedRecipeId == featuredRecipe.id) null else featuredRecipe.id
                    },
                    onNavigateToDetail = onNavigateToDetail
                )
                Spacer(modifier = Modifier.height(24.dp))
            }

            item {
                Text(
                    text = "Daftar Resep",
                    style = MaterialTheme.typography.headlineSmall.copy(fontStyle = FontStyle.Italic),
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(recipes.drop(1), key = { it.id }) { recipe ->
                Column(modifier = Modifier.padding(horizontal = 24.dp)) {
                    RecipeListCard(
                        recipe = recipe,
                        isExpanded = expandedRecipeId == recipe.id,
                        onToggle = {
                            expandedRecipeId = if (expandedRecipeId == recipe.id) null else recipe.id
                        },
                        onNavigateToDetail = onNavigateToDetail
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                }
            }

            item {
                Spacer(modifier = Modifier.height(14.dp))
                QuickTipsCard()
            }
        }
    }
}

@Composable
fun CategoryChip(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Surface(
        shape = CircleShape,
        color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else surfaceContainerHigh,
        modifier = modifier.clickable { onClick() }
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
fun FeaturedRecipeCard(
    recipe: Recipe,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    onNavigateToDetail: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .clickable { onToggle() },
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
                    model = recipe.imageUrl,
                    contentDescription = recipe.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Surface(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.TopStart)
                ) {
                    Text(
                        text = "TERPOPULER",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
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
                        Row(modifier = Modifier.padding(bottom = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Star, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(recipe.rating, style = MaterialTheme.typography.labelMedium)
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(recipe.time, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.secondary)
                        }
                        Text(
                            text = recipe.name,
                            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Black)
                        )
                        if (isExpanded) {
                            BriefRecipeDetail(recipe = recipe, onNavigateToDetail = onNavigateToDetail)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RecipeListCard(
    recipe: Recipe,
    isExpanded: Boolean,
    onToggle: () -> Unit,
    onNavigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.clickable { onToggle() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = surfaceContainerLow,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4f / 3f)
        ) {
            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = recipe.name,
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
            Text(recipe.name, style = MaterialTheme.typography.headlineSmall)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Star, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                Text(recipe.rating, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(recipe.time.uppercase(), style = MaterialTheme.typography.labelSmall, letterSpacing = 1.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier.size(4.dp).background(md_theme_light_outlineVariant, CircleShape))
            Spacer(modifier = Modifier.width(8.dp))
            Text(recipe.difficulty.uppercase(), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.secondary, letterSpacing = 1.sp)
        }
        if (isExpanded) {
            Surface(
                color = Color.White.copy(alpha = 0.95f),
                shape = RoundedCornerShape(16.dp),
                tonalElevation = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    BriefRecipeDetail(recipe = recipe, onNavigateToDetail = onNavigateToDetail)
                }
            }
        }
    }
}

@Composable
private fun BriefRecipeDetail(recipe: Recipe, onNavigateToDetail: (String) -> Unit) {
    Spacer(modifier = Modifier.height(12.dp))
    Text(
        text = recipe.description,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
    Spacer(modifier = Modifier.height(12.dp))
    Text(
        text = "Bahan utama: ${recipe.ingredients.take(3).joinToString(", ")}",
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(
        onClick = { onNavigateToDetail(recipe.id) },
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Text("LIHAT RESEP", style = MaterialTheme.typography.labelSmall, letterSpacing = 1.sp)
    }
}

@Composable
private fun QuickTipsCard() {
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        Surface(
            color = surfaceContainerLow,
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(modifier = Modifier.padding(start = 4.dp).background(MaterialTheme.colorScheme.primary)) {
                Column(
                    modifier = Modifier
                        .background(surfaceContainerLow)
                        .padding(24.dp)
                        .fillMaxWidth()
                ) {
                    Text("QUICK TIPS", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary, letterSpacing = 2.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Rahasia Sambal Bajak yang Gurih", style = MaterialTheme.typography.headlineSmall.copy(fontStyle = FontStyle.Italic))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Gunakan terasi yang sudah dibakar untuk aroma yang lebih kuat.", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("BACA TIPS", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
                        Icon(Icons.Default.ArrowForward, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }
    }
}
