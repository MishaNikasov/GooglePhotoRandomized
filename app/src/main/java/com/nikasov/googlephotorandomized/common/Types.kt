package com.nikasov.googlephotorandomized.common

import androidx.lifecycle.MutableLiveData
import com.nikasov.googlephotorandomized.data.remote.model.MediaItem

typealias PhotosData = MutableLiveData<Resource<List<MediaItem>>>
