package com.nikasov.googlephotorandomized.ui.fragment.auth

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.material.transition.MaterialFadeThrough
import com.google.firebase.auth.GoogleAuthProvider
import com.nikasov.googlephotorandomized.R
import com.nikasov.googlephotorandomized.common.Constants.GOOGLE_SIGN_IN_REQUEST
import com.nikasov.googlephotorandomized.common.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_auth.*
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragment: Fragment (R.layout.fragment_auth) {

    @Inject
    lateinit var signInOption : GoogleSignInClient

    private val viewModel: AuthViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enterTransition = MaterialFadeThrough()

        initUi()
    }

    private fun initUi() {

        authByGoogleBtn.setOnClickListener {
            signInByGoogle()
        }

        viewModel.isAuthorised.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    Toast.makeText(requireContext(), "Done", Toast.LENGTH_LONG).show()
                }
                is Resource.Success -> {
                    findNavController().navigate(R.id.toRootFragment)
                }
            }
        })
    }

    private fun signInByGoogle() {
        signInOption.signInIntent.also {
            startActivityForResult(it, GOOGLE_SIGN_IN_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN_REQUEST) {
            try {
                val account = GoogleSignIn.getSignedInAccountFromIntent(data).result
                account?.let {
                    googleAuthToFirebase(it)
                }
            } catch (e : Exception) {
                Timber.d(e.localizedMessage)
            }
        }
    }

    private fun googleAuthToFirebase(signInAccount: GoogleSignInAccount) {
        val credentials = GoogleAuthProvider.getCredential(signInAccount.idToken, null)
        viewModel.signInWithGoogle(credentials, signInAccount.idToken!!)
    }

}