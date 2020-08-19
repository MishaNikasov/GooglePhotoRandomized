package com.nikasov.googlephotorandomized.data.remote.repository

import com.nikasov.googlephotorandomized.data.remote.model.MediaItem
import com.nikasov.googlephotorandomized.data.remote.services.GooglePhotoService
import retrofit2.Response
import javax.inject.Inject

class GooglePhotoRepository @Inject constructor(
    private val googlePhotoService: GooglePhotoService
) {

    suspend fun getAllPhotos() : Response<List<MediaItem>> {
        return googlePhotoService.getAllPhotos()
    }

}