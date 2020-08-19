package com.nikasov.googlephotorandomized.ui.fragment.root

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialFadeThrough
import com.google.firebase.auth.FirebaseAuth
import com.nikasov.googlephotorandomized.R
import com.nikasov.googlephotorandomized.common.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_root.*
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class RootFragment: Fragment (R.layout.fragment_root) {

    private val viewModel: RootViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enterTransition = MaterialFadeThrough()
        initUi()
    }

    private fun initUi() {
        viewModel.getAllPhoto()

        viewModel.photosList.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                }
                is Resource.Error -> {
                    Timber.e(it.message)
                }
            }
        })
    }

}