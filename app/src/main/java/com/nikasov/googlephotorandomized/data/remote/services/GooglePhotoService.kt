package com.nikasov.googlephotorandomized.data.remote.services

import com.nikasov.googlephotorandomized.data.remote.model.MediaItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GooglePhotoService {

    @GET("mediaItems")
    suspend fun getAllPhotos(
        @Query("offset") offset : Int = 0
    ) : Response<List<MediaItem>>

}