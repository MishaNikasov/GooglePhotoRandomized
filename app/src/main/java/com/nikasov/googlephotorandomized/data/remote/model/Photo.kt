package com.nikasov.googlephotorandomized.data.remote.model

data class Photo(
    val apertureFNumber: Int,
    val cameraMake: String,
    val cameraModel: String,
    val focalLength: Double,
    val isoEquivalent: Int
)