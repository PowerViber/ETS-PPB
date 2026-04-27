package com.example.resepkita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.resepkita.data.CookingPhoto
import com.example.resepkita.data.RecipeRepository
import com.example.resepkita.ui.screens.DetailScreen
import com.example.resepkita.ui.screens.FavoritesScreen
import com.example.resepkita.ui.screens.HomeScreen
import com.example.resepkita.ui.screens.profile.ProfileScreen
import com.example.resepkita.ui.screens.SearchScreen
import com.example.resepkita.ui.theme.ResepKitaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ResepKitaTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val favoriteRecipeIds = remember {
        mutableStateListOf(*RecipeRepository.recipes.filter { it.isFavorite }.map { it.id }.toTypedArray())
    }
    val plannedRecipeIds = remember { mutableStateListOf<String>() }
    val cookingPhotos = remember { mutableStateListOf<CookingPhoto>() }

    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen(
                    onNavigateToDetail = { recipeId -> navController.navigate("detail/$recipeId") },
                    onNavigateToSearch = { navController.navigateRootTab("search") }
                )
            }
            composable("search") {
                SearchScreen(onNavigateToDetail = { recipeId -> navController.navigate("detail/$recipeId") })
            }
            composable(
                route = "detail/{recipeId}",
                arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
            ) { backStackEntry ->
                DetailScreen(
                    recipeId = backStackEntry.arguments?.getString("recipeId"),
                    favoriteRecipeIds = favoriteRecipeIds,
                    plannedRecipeIds = plannedRecipeIds,
                    cookingPhotos = cookingPhotos,
                    onToggleFavorite = { recipeId ->
                        if (favoriteRecipeIds.contains(recipeId)) {
                            favoriteRecipeIds.remove(recipeId)
                        } else {
                            favoriteRecipeIds.add(recipeId)
                        }
                    },
                    onAddCookingPlan = { recipeId ->
                        if (!plannedRecipeIds.contains(recipeId)) {
                            plannedRecipeIds.add(recipeId)
                        }
                    },
                    onRemoveCookingPlan = { recipeId ->
                        plannedRecipeIds.remove(recipeId)
                    },
                    onCookingPhotoCaptured = { recipeId, bitmap ->
                        val isDuplicate = cookingPhotos.any { it.bitmap.sameAs(bitmap) }
                        if (!isDuplicate) {
                            cookingPhotos.add(
                                CookingPhoto(
                                    id = System.nanoTime(),
                                    recipeId = recipeId,
                                    bitmap = bitmap
                                )
                            )
                        }
                        plannedRecipeIds.remove(recipeId)
                    },
                    onCookingFinished = { },
                    onNavigateBack = { navController.popBackStack() }
                )
            }
            composable("favorites") {
                FavoritesScreen(
                    favoriteRecipeIds = favoriteRecipeIds,
                    onNavigateToDetail = { recipeId -> navController.navigate("detail/$recipeId") }
                )
            }
            composable("profile") {
                ProfileScreen(
                    plannedRecipeCount = plannedRecipeIds.size,
                    cookedRecipeIds = cookingPhotos.map { it.recipeId },
                    favoriteRecipeIds = favoriteRecipeIds
                )
            }
        }

        // Show Glass Bottom Navigation Bar only if we are not on the detail screen
        if (currentRoute?.startsWith("detail") != true) {
            GlassBottomNavigation(
                currentRoute = currentRoute ?: "home",
                onNavigate = { route ->
                    if (route != currentRoute) {
                        navController.navigateRootTab(route)
                    }
                },
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 24.dp)
            )
        }
    }
}

private fun NavController.navigateRootTab(route: String) {
    if (route == "home" && popBackStack("home", inclusive = false)) return

    navigate(route) {
        popUpTo("home") {
            saveState = false
        }
        launchSingleTop = true
        restoreState = false
    }
}

@Composable
fun GlassBottomNavigation(
    currentRoute: String,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(0.92f)
            .height(72.dp)
            .shadow(
                elevation = 20.dp,
                shape = CircleShape,
                ambientColor = Color.Black.copy(alpha = 0.1f),
                spotColor = Color.Black.copy(alpha = 0.1f)
            )
            .clip(CircleShape),
        color = Color(0xCCFBF9F5), // Semi-transparent background mapped from surface
        contentColor = Color(0xFF7c2d12) // Stone 500 equivalent 
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavItem(
                icon = Icons.Default.Home,
                label = "Home",
                isSelected = currentRoute == "home",
                onClick = { onNavigate("home") }
            )
            NavItem(
                icon = Icons.Default.Search,
                label = "Search",
                isSelected = currentRoute == "search",
                onClick = { onNavigate("search") }
            )
            NavItem(
                icon = Icons.Default.Favorite,
                label = "Favorites",
                isSelected = currentRoute == "favorites",
                onClick = { onNavigate("favorites") }
            )
            NavItem(
                icon = Icons.Default.Person,
                label = "Profile",
                isSelected = currentRoute == "profile",
                onClick = { onNavigate("profile") }
            )
        }
    }
}

@Composable
fun NavItem(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    if (isSelected) {
        Column(
            modifier = Modifier
                .size(48.dp)
                .background(Color(0xFFc2410c), CircleShape) // bg-orange-700
                .clickable { onClick() },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = icon, contentDescription = label, tint = Color.White)
        }
    } else {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .clickable { onClick() },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = icon, contentDescription = label, tint = Color(0xFF78716c)) // text-stone-500
            Spacer(modifier = Modifier.height(2.dp))
            Text(label, style = MaterialTheme.typography.labelSmall, fontSize = 8.sp, color = Color(0xFF78716c), letterSpacing = 1.sp)
        }
    }
}
