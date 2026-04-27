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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.resepkita.ui.screens.DetailScreen
import com.example.resepkita.ui.screens.FavoritesScreen
import com.example.resepkita.ui.screens.HomeScreen
import com.example.resepkita.ui.screens.ProfileScreen
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

    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomeScreen(
                    onNavigateToDetail = { navController.navigate("detail") },
                    onNavigateToSearch = { navController.navigate("search") }
                )
            }
            composable("search") {
                SearchScreen()
            }
            composable("detail") {
                DetailScreen(onNavigateBack = { navController.popBackStack() })
            }
            composable("favorites") {
                FavoritesScreen(onNavigateToDetail = { navController.navigate("detail") })
            }
            composable("profile") {
                ProfileScreen()
            }
        }

        // Show Glass Bottom Navigation Bar only if we are not on the detail screen
        if (currentRoute != "detail") {
            GlassBottomNavigation(
                currentRoute = currentRoute ?: "home",
                onNavigate = { route ->
                    if (route != currentRoute) {
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 24.dp)
            )
        }
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
