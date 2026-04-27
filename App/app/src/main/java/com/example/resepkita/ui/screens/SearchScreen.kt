package com.example.resepkita.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Search
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
fun SearchScreen() {
    Scaffold(
        topBar = {
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
                .padding(24.dp)
        ) {
            // Search Input
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Cari nama resep atau bahan...", color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = md_theme_light_outline) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                    unfocusedBorderColor = Color.Transparent,
                    focusedContainerColor = surfaceContainerHighest,
                    unfocusedContainerColor = surfaceContainerHighest
                )
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Popular Ingredients
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
                    text = "LIHAT MUSIM INI",
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
                PopularIngredient(
                    name = "Ayam",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAHhCRCCo7MsNqHhDv_pIc4oxVX912gxf2MVrLKWg_I0V59oSDOd6jEYPv3POeGOz6oWanxpOeLVnyIvJJvoo06CDaiU-vMMMlQEOflwphJdtPSKSbrJKt_52ZYDEL-4tVUvIR0QiZOI8MDNtRPJ_89VEUwP_QKW-1qlqJtC_wVCo8TsKsp6-LTde2ZFDqVtYDww5NvdeUIPZLsR5ZS3ByLDQZm4QDqv8b5bhBXvv72tGTS1taFrDTWCRuwLiKYmy8qNxwGnU6Xa8PE",
                    modifier = Modifier.weight(1f)
                )
                PopularIngredient(
                    name = "Daging",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAKe6NGUwKAo05SA-Bckj9QzarFm1smE-U4pahXY58iSuGgDWSrzZbpke3ZK2nO4P7ZhlDCDDp5wDeAJF0B5SnxlInpu_VQJJa5cTlzP1tOHXCzFibw5eaXvsufyPX00jrQ-o2DfJ0VwvE3apwBIlrqUWE9MLs7SL3_eQs-sZ4fo_UOhysoJB-OJtBJMvjfEajSDE8ipXIXbUJdj9His5I1de0L-kGdvH2GNwDbpd5av0NeJIB-DCyaE4NeyArLmE7hbnXCyJn24otM",
                    modifier = Modifier.weight(1f)
                )
                PopularIngredient(
                    name = "Tempe",
                    imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCYHdbf_wFpmvZYtlqVwbwkaJhFqzE-ofETtfh-pnC-IzBrqQe1WE4CSZAu8u_VEWYk6vaAU3-0epoKv4A3Or-HfaKNIElKdXA1VNb5-XsgLVYxDdL_yZaYKXgmbZWTmpFGpobEgFSKAa2AFEp9NIenmRN6_UT74xj8W4JplRBM-ftuiRkNO_7ihljdEE2SHvVoKlgai9D4ItD_Zp55sgLBgS2dZ-DDv7vw7JPwlK7BakgYGr7YPU6ABfrc-Pl9ButlIjahppPHBYeF",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Recent Searches
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Pencarian Terbaru",
                    style = MaterialTheme.typography.headlineSmall.copy(fontStyle = FontStyle.Italic)
                )
                Text(
                    text = "HAPUS SEMUA",
                    style = MaterialTheme.typography.labelSmall,
                    color = md_theme_light_outline,
                    letterSpacing = 1.sp,
                    modifier = Modifier.clickable { }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            RecentSearchItem("Rendang Daging Sapi", "24 Resep Ditemukan", "https://lh3.googleusercontent.com/aida-public/AB6AXuC_BkO_Xzu3Io7_ljR9nykAO-V0H6KjvE2OhHTyCkzqZS5P7HA6t_bNi62R-_Rn_iRQBO1i1kRmOAQ0sw0IGrkD4nqOQ-Bx9xvt3lSK6zKmQpbC9Mts1mYTpNr9KZY4akKcAUr5iIxjDQars1pdiVVO2_1Qa5H0DVBqKR6snrSH6ERsjB6-Zgo2B63Zu27YcA2Hw4J433srdLIGbLlg-PuMA9MNJjp6hd63nn5XH3co0EBdjKskpmSxW7T7PmrR3sbFySDVhJU9YIG_")
            Spacer(modifier = Modifier.height(12.dp))
            RecentSearchItem("Nasi Goreng Kampung", "12 Resep Ditemukan", "https://lh3.googleusercontent.com/aida-public/AB6AXuAr_xdf7lZ9r6xjtib-CMkDYVRuxq6lNz4yA_DPHAPyb3fLoXEh7rI7C5V0JDOUdWfKsoxS3UjtXA_Pvyh5U1CMqDvv6qjA4tOMrfBN_zCyeBQeTN2YmYG7bJ5b4G-Ftam5FOyjmr8EcA1VFS1aO2x7CCZyFday_yx2VtXkGDRaWtUJpm7Sf5H3hqrWVauJuGggipRwYCkbWOtQBW9HPjHvaxviFOxvLhmJjQOvwRaxslMFcdnuTnoq01z3LH8RgcLmCPIsFka7rJqd")
            Spacer(modifier = Modifier.height(12.dp))
            RecentSearchItem("Sate Ayam Madura", "8 Resep Ditemukan", "https://lh3.googleusercontent.com/aida-public/AB6AXuCYyYV51i8vwFMrQM3Q8wjQq4R1_qoCkuHtoKzYKdUOrjr9V6KHQXmA91NltRm-QWhX10kbkXNoaDO7p3QrtF-v6jChZW7z8bHh0JcWFzzObgFgn8XjkDEdbjgHfuRNIyq12PncM4Wf1cNj4M1VTGhCYa9viCa4CUf2rngSi2w4T0nGu_Jc08hMLn9NQXx07bSEP2x3AJr6Igrf7rOcoga3a31mZL0ATFJY6r6N7kpt-knd39c4o13BSVxGGjiqj2oC1FlZxnjIIN49")

            Spacer(modifier = Modifier.height(40.dp))

            // Feature Spotlight
            Surface(
                color = md_theme_light_tertiaryContainer,
                shape = RoundedCornerShape(32.dp),
                modifier = Modifier.fillMaxWidth().height(200.dp)
            ) {
                Box {
                    Column(modifier = Modifier.padding(32.dp).fillMaxWidth(0.6f)) {
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
                    // Decorative icon could go here on the right
                }
            }
            
            Spacer(modifier = Modifier.height(100.dp)) // Bottom Nav space
        }
    }
}

@Composable
fun PopularIngredient(name: String, imageUrl: String, modifier: Modifier = Modifier) {
    Surface(
        color = surfaceContainerLow,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.clickable { }
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
fun RecentSearchItem(title: String, subtitle: String, imageUrl: String) {
    Surface(
        color = surfaceContainerLowest,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth().clickable { }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(title, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
                    Text(
                        subtitle,
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
