package com.nikasov.googlephotorandomized.ui.fragment.root

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.nikasov.googlephotorandomized.common.PhotosData
import com.nikasov.googlephotorandomized.common.Resource
import com.nikasov.googlephotorandomized.data.remote.model.MediaItem
import com.nikasov.googlephotorandomized.data.remote.repository.GooglePhotoRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber

class RootViewModel @ViewModelInject constructor(
    private val auth : FirebaseAuth,
    private val googlePhotoRepository: GooglePhotoRepository
) : ViewModel() {

    val photosList: PhotosData = MutableLiveData()

    fun getAllPhoto() {
        viewModelScope.launch {
            handlePhotosResponse(googlePhotoRepository.getAllPhotos())
        }
    }

    private fun handlePhotosResponse(response: Response<List<MediaItem>>){
        photosList.postValue(Resource.Loading())
        if (response.isSuccessful) {
            response.body()?.let { list ->
                photosList.postValue(Resource.Success(list))
                list.forEach { item ->
                    Timber.d(item.productUrl)
                }
            }
        } else {
            photosList.postValue(Resource.Error("Кто бы мог подумать? ${response.raw()}"))
        }
    }
}