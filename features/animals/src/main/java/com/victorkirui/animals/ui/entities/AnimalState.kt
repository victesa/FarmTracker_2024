package com.victorkirui.animals.ui.entities

import android.graphics.Bitmap
import android.net.Uri
import androidx.compose.ui.graphics.ImageBitmap

data class AnimalState(
    val name: String = "",
    val age: String = "",
    val animalImage: String? = null
)
