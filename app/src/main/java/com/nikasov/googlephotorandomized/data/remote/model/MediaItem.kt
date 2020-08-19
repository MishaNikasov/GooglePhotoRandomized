package com.nikasov.googlephotorandomized.data.remote.model

data class MediaItem(
    val baseUrl: String,
    val filename: String,
    val id: String,
    val mediaMetadata: MediaMetadata,
    val mimeType: String,
    val productUrl: String
)