package com.nikasov.googlephotorandomized.ui.fragment.auth

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.nikasov.googlephotorandomized.data.Prefs
import com.nikasov.googlephotorandomized.common.Resource
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import timber.log.Timber

class AuthViewModel @ViewModelInject constructor(
    private val prefs: Prefs,
    private val auth : FirebaseAuth
) : ViewModel() {

    var isAuthorised : MutableLiveData<Resource<Boolean>> = MutableLiveData()

    private fun saveUser(token: String) {
        prefs.saveIsLogged()
        prefs.saveToken(token)

        Timber.d("User token: $token")
    }

    fun signInWithGoogle (googleAuthCredential : AuthCredential, token: String) {
        viewModelScope.launch {
            isAuthorised.postValue(Resource.Loading())
            auth.signInWithCredential(googleAuthCredential).await()
            saveUser(token)
            isAuthorised.postValue(Resource.Success(null))
        }
    }
}