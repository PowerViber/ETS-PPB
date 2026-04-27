package com.example.resepkita.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalDining
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.resepkita.data.RecipeRepository
import com.example.resepkita.ui.components.ResepKitaTopBar
import com.example.resepkita.ui.theme.md_theme_light_outline
import com.example.resepkita.ui.theme.surfaceContainerHigh
import com.example.resepkita.ui.theme.surfaceContainerHighest
import com.example.resepkita.ui.theme.surfaceContainerLow
import com.example.resepkita.ui.theme.surfaceContainerLowest

@Composable
fun ProfileScreen(
    plannedRecipeCount: Int,
    cookedRecipeIds: List<String>,
    favoriteRecipeIds: List<String>
) {
    Scaffold(
        topBar = {
            ResepKitaTopBar()
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            ProfileHero()
            Spacer(modifier = Modifier.height(28.dp))
            CookingStats(
                plannedRecipeCount = plannedRecipeCount,
                cookedCount = cookedRecipeIds.size,
                favoriteRecipeCount = favoriteRecipeIds.size
            )
            Spacer(modifier = Modifier.height(32.dp))
            TasteProfile(cookedRecipeIds = cookedRecipeIds)
            Spacer(modifier = Modifier.height(32.dp))
            // ProfileMenu(plannedRecipeCount = plannedRecipeCount)
            Spacer(modifier = Modifier.height(120.dp))
        }
    }
}

@Composable
private fun ProfileHero() {
    Surface(
        color = surfaceContainerLow,
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = "https://lh3.googleusercontent.com/aida-public/AB6AXuDX3yUSsAdSt2v5k_E85NHWbnnWj2peK7IdJcPWONAMjDjmB2OJLkLAwghRBXSj3raqeQTBpOny6q9aAyzyWRN488yI-whosdG0KDLu8rPL41mf7iTKlVCofZ5kHubjNjQOZyWKfW-5Q_EG5Hb_gGH_DvYPsXbuUZJDa5pEcCtkwR9PI_bKFoB49L3fqiYvxxeVpSX89B7Nxav-0-U04Kz7SKEaA5fWjC3qkJvm2tW-LOj_1ogGpEN7n30KzsELUDeYT1GAr34SQrcH",
                    contentDescription = "Profile photo",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(88.dp)
                        .clip(CircleShape)
                        .background(surfaceContainerHigh)
                )
                Spacer(modifier = Modifier.width(18.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "PENJELAJAH RASA",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.secondary,
                        letterSpacing = 2.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Dzaky",
                        style = MaterialTheme.typography.displaySmall.copy(fontStyle = FontStyle.Italic)
                    )
                    Text(
                        text = "Level 4 - Peracik Bumbu",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                // Surface(
                //     color = MaterialTheme.colorScheme.primary,
                //     shape = CircleShape,
                //     modifier = Modifier.size(44.dp)
                // ) {
                //     Box(contentAlignment = Alignment.Center) {
                //         Icon(Icons.Default.Edit, contentDescription = "Edit profile", tint = Color.White, modifier = Modifier.size(20.dp))
                //     }
                // }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Surface(
                color = surfaceContainerLowest,
                shape = RoundedCornerShape(18.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Untung udah ga beda agama",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(18.dp)
                )
            }
        }
    }
}

@Composable
private fun CookingStats(plannedRecipeCount: Int, cookedCount: Int, favoriteRecipeCount: Int) {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth()) {
        ProfileStat(plannedRecipeCount.toString(), "Rencana", Icons.Default.RestaurantMenu, Modifier.weight(1f))
        ProfileStat(favoriteRecipeCount.toString(), "Favorit", Icons.Default.Favorite, Modifier.weight(1f))
        ProfileStat(cookedCount.toString(), "Masakan", Icons.Default.Star, Modifier.weight(1f))
    }
}

@Composable
private fun ProfileStat(value: String, label: String, icon: ImageVector, modifier: Modifier = Modifier) {
    Surface(
        color = surfaceContainerHighest,
        shape = RoundedCornerShape(18.dp),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(vertical = 18.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(22.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall.copy(fontStyle = FontStyle.Italic)
            )
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
private fun TasteProfile(cookedRecipeIds: List<String>) {
    val cookedRecipes = cookedRecipeIds.map { RecipeRepository.findById(it) }
    val cookedCount = cookedRecipes.size
    val beginnerCount = cookedRecipes.count { it.difficulty == "Pemula" }
    val mediumCount = cookedRecipes.count { it.difficulty == "Menengah" }
    val advancedCount = cookedRecipes.count { it.difficulty == "Mahir" }

    Surface(
        color = Color(0xFF8d6e63),
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "MASAKAN SELESAI",
                style = MaterialTheme.typography.labelSmall,
                color = Color.White.copy(alpha = 0.82f),
                letterSpacing = 2.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = if (cookedCount == 0) "Belum Ada Masakan" else "$cookedCount Foto Masakan",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontStyle = FontStyle.Italic,
                    color = Color.White
                )
            )
            Spacer(modifier = Modifier.height(18.dp))
            TasteRow("Pemula", progressFor(beginnerCount, cookedCount), beginnerCount)
            TasteRow("Menengah", progressFor(mediumCount, cookedCount), mediumCount)
            TasteRow("Mahir", progressFor(advancedCount, cookedCount), advancedCount)
        }
    }
}

@Composable
private fun TasteRow(label: String, progress: Float, count: Int) {
    Column(modifier = Modifier.padding(vertical = 7.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(label, style = MaterialTheme.typography.labelMedium, color = Color.White)
            Text("$count foto", style = MaterialTheme.typography.labelSmall, color = Color.White.copy(alpha = 0.78f))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.22f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress)
                    .height(8.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )
        }
    }
}

private fun progressFor(count: Int, total: Int): Float {
    if (total == 0) return 0f
    return count.toFloat() / total.toFloat()
}

// @Composable
// private fun ProfileMenu(plannedRecipeCount: Int) {
//     Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
//         ProfileMenuItem(Icons.Default.Schedule, "Rencana Masak", "$plannedRecipeCount resep menunggu minggu ini")
//         ProfileMenuItem(Icons.Default.LocalDining, "Preferensi Bahan", "Ayam, tempe, cabai, daun jeruk")
//         ProfileMenuItem(Icons.Default.Notifications, "Pengingat Dapur", "Aktif untuk jadwal makan malam")
//         ProfileMenuItem(Icons.Default.Person, "Akun & Privasi", "Kelola profil ResepKita")
//     }
// }

// @Composable
// private fun ProfileMenuItem(icon: ImageVector, title: String, subtitle: String) {
//     Surface(
//         color = surfaceContainerLowest,
//         shape = RoundedCornerShape(18.dp),
//         modifier = Modifier
//             .fillMaxWidth()
//             .clickable { }
//     ) {
//         Row(
//             modifier = Modifier.padding(18.dp),
//             verticalAlignment = Alignment.CenterVertically
//         ) {
//             Surface(color = surfaceContainerHigh, shape = CircleShape, modifier = Modifier.size(44.dp)) {
//                 Box(contentAlignment = Alignment.Center) {
//                     Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(21.dp))
//                 }
//             }
//             Spacer(modifier = Modifier.width(16.dp))
//             Column(modifier = Modifier.weight(1f)) {
//                 Text(title, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
//                 Text(subtitle, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
//             }
//             Icon(Icons.Default.ChevronRight, contentDescription = null, tint = md_theme_light_outline, modifier = Modifier.size(20.dp))
//         }
//     }
// }
