package com.bootcamp.inviobootcampbp_sepette.ui.fragment

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.bootcamp.inviobootcampbp_sepette.R
import com.bootcamp.inviobootcampbp_sepette.databinding.FragmentSignInBinding
import com.bootcamp.inviobootcampbp_sepette.ui.activity.LoginActivity
import com.bootcamp.inviobootcampbp_sepette.ui.activity.MainActivity
import com.bootcamp.inviobootcampbp_sepette.utils.changePage
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.Exception

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.signInFragment = this
        auth = Firebase.auth

        //Configure Google signin
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("501213869871-n9ibnqije2g278tcn9a3q70hvuka1aps.apps.googleusercontent.com")
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)

            binding.navText.setOnClickListener {
                Navigation.changePage(it, R.id.action_signInFragment_to_signUpFragment)
        }
    }

     fun signin(email: String, password: String) {

        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        Log.e("Sign in", "signInWithEmail:success")
                        val intent = Intent(activity, MainActivity::class.java)
                        startActivity(intent)
                        activity?.finish()
                    } else {
                        Log.e("Sign in", "signInWithEmail:failure", task.exception)
                    }
                }
        } else
            Toast.makeText(requireActivity(), "e-mail veya şifre boş", Toast.LENGTH_SHORT).show()
    }

    fun signInGoogle(){
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent,RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.e(ContentValues.TAG,"FirebaseAuthWithGoogle: "+ account.id)
                signinGoogleResult(account.idToken!!)
            }catch (e: ApiException){
                Log.e(ContentValues.TAG,"Gogle signed failed",e)
            }
        }
    }

    private fun signinGoogleResult(idToken: String) {
        when {
            idToken != null -> {
                val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                auth.signInWithCredential(firebaseCredential)
                    .addOnCompleteListener(requireActivity()) { task ->
                        if (task.isSuccessful) {
                            Log.e("Signin Google", "signInWithCredential:success")
                            val intent = Intent(activity, MainActivity::class.java)
                            startActivity(intent)
                            activity?.finish()
                        } else {
                            Log.e("Signin Google", "signInWithCredential:failure", task.exception)
                        }
                    }
            }
            else -> {
                Log.e("Signin Google", "No ID token!")
            }
        }
    }

    companion object{
        const val RC_SIGN_IN = 1001
    }
}
