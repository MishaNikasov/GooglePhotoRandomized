package com.nikasov.googlephotorandomized.ui.activity

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.nikasov.googlephotorandomized.data.Prefs

class MainViewModel @ViewModelInject constructor(
    private val prefs: Prefs
) : ViewModel() {

    val isLogged = prefs.isLogged().value!!

}