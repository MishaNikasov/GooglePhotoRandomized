package com.nikasov.googlephotorandomized.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.nikasov.googlephotorandomized.R
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpUi()
    }

    private fun setUpUi() {
        setUpController()

        if (viewModel.isLogged) {
            goToProfile()
        }
    }

    private fun setUpController() {
        findNavController(R.id.hostFragment).addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {

            }
        }
    }

    private fun goToProfile() {
        findNavController(R.id.hostFragment).navigate(R.id.toRootFragment)
    }
}