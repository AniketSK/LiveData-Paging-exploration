package com.aniketkadam.tryoutstuff

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageData(
    @PrimaryKey
    val id: String,
    val name: String,
    val url: String
)