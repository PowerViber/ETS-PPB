package com.example.resepkita.data

import android.graphics.Bitmap

data class CookingPhoto(
    val id: Long,
    val recipeId: String,
    val bitmap: Bitmap
)
