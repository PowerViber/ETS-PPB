package com.example.resepkita.ui.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.resepkita.data.CookingPhoto
import com.example.resepkita.data.RecipeRepository
import com.example.resepkita.ui.components.ResepKitaTopBar
import com.example.resepkita.ui.theme.surfaceContainerHighest
import com.example.resepkita.ui.theme.surfaceContainerLow
import com.example.resepkita.ui.theme.surfaceContainerLowest

@Composable
fun DetailScreen(
    recipeId: String?,
    favoriteRecipeIds: List<String>,
    plannedRecipeIds: List<String>,
    cookingPhotos: List<CookingPhoto>,
    onToggleFavorite: (String) -> Unit,
    onAddCookingPlan: (String) -> Unit,
    onRemoveCookingPlan: (String) -> Unit,
    onCookingPhotoCaptured: (String, android.graphics.Bitmap) -> Unit,
    onCookingFinished: () -> Unit,
    onNavigateBack: () -> Unit
) {
    val recipe = RecipeRepository.findById(recipeId)
    val isFavorite = favoriteRecipeIds.contains(recipe.id)
    val isPlanned = plannedRecipeIds.contains(recipe.id)
    val recipePhotos = cookingPhotos.filter { it.recipeId == recipe.id }
    val checkedIngredients = remember(recipe.id) { mutableStateListOf<String>() }
    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        if (bitmap != null) {
            onCookingPhotoCaptured(recipe.id, bitmap)
            checkedIngredients.clear()
            onCookingFinished()
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            Box(modifier = Modifier.fillMaxWidth().height(400.dp)) {
                AsyncImage(
                    model = recipe.imageUrl,
                    contentDescription = recipe.name,
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

            Column(modifier = Modifier.padding(horizontal = 24.dp).offset(y = (-40).dp)) {
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(
                            text = "${recipe.region.uppercase()} HERITAGE",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.secondary,
                            letterSpacing = 2.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = recipe.name,
                            style = MaterialTheme.typography.displayMedium.copy(fontStyle = FontStyle.Italic)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = recipe.description,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            StatCard("Waktu", recipe.time)
                            StatCard("Porsi", recipe.servings)
                            StatCard("Level", recipe.difficulty)
                        }

                        Spacer(modifier = Modifier.height(32.dp))

                        Text(
                            "Bahan-bahan",
                            style = MaterialTheme.typography.headlineSmall.copy(fontStyle = FontStyle.Italic),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(modifier = Modifier.height(4.dp).width(120.dp).background(MaterialTheme.colorScheme.primary, CircleShape))

                        Spacer(modifier = Modifier.height(16.dp))
                        recipe.ingredients.forEach { ingredient ->
                            IngredientItem(
                                text = ingredient,
                                checked = checkedIngredients.contains(ingredient),
                                onCheckedChange = { isChecked ->
                                    if (isChecked) {
                                        if (!checkedIngredients.contains(ingredient)) {
                                            checkedIngredients.add(ingredient)
                                        }
                                    } else {
                                        checkedIngredients.remove(ingredient)
                                    }
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(40.dp))

                        Text(
                            "Cara Pembuatan",
                            style = MaterialTheme.typography.headlineSmall.copy(fontStyle = FontStyle.Italic),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Box(modifier = Modifier.height(4.dp).width(120.dp).background(MaterialTheme.colorScheme.primary, CircleShape))

                        Spacer(modifier = Modifier.height(18.dp))
                        recipe.steps.forEachIndexed { index, step ->
                            InstructionStep(
                                number = (index + 1).toString().padStart(2, '0'),
                                title = step.substringBefore(".").ifBlank { "Langkah ${index + 1}" },
                                desc = step
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                        }

                        if (recipePhotos.isNotEmpty()) {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "FOTO HASIL MASAK",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                                letterSpacing = 2.sp
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            recipePhotos.forEachIndexed { index, photo ->
                                Surface(
                                    shape = RoundedCornerShape(20.dp),
                                    color = surfaceContainerLow,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(220.dp)
                                ) {
                                    Image(
                                        bitmap = photo.bitmap.asImageBitmap(),
                                        contentDescription = "Foto hasil masak ${recipe.name} ${index + 1}",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxSize()
                                    )
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        }
                        Spacer(modifier = Modifier.height(96.dp))
                    }
                }
            }
        }

        ResepKitaTopBar(
            showBackButton = true,
            onNavigateBack = onNavigateBack,
            containerAlpha = 0.5f,
            actions = {
                IconButton(onClick = { onToggleFavorite(recipe.id) }) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                        tint = Color(0xFF9a3412)
                    )
                }
            },
            modifier = Modifier.align(Alignment.TopCenter)
        )

        if (isPlanned) {
            Surface(
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.94f),
                shape = CircleShape,
                shadowElevation = 8.dp,
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 32.dp)
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { cameraLauncher.launch(null) },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Text("SELESAI MEMASAK", style = MaterialTheme.typography.labelSmall, letterSpacing = 1.sp)
                    }
                    Button(
                        onClick = { onRemoveCookingPlan(recipe.id) },
                        colors = ButtonDefaults.buttonColors(containerColor = surfaceContainerHighest, contentColor = MaterialTheme.colorScheme.onSurfaceVariant)
                    ) {
                        Text("HAPUS RENCANA", style = MaterialTheme.typography.labelSmall, letterSpacing = 1.sp)
                    }
                }
            }
        } else {
            ExtendedFloatingActionButton(
                onClick = { onAddCookingPlan(recipe.id) },
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 32.dp),
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            ) {
                Text("MULAI MEMASAK", style = MaterialTheme.typography.labelMedium, letterSpacing = 1.sp)
            }
        }
    }
}

@Composable
fun StatCard(label: String, value: String) {
    Surface(
        color = surfaceContainerLow,
        shape = CircleShape
    ) {
        Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)) {
            Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun IngredientItem(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        color = surfaceContainerLowest,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.primary)
            )
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
