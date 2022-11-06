package com.bootcamp.inviobootcampbp_sepette.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.bootcamp.inviobootcampbp_sepette.R
import com.bootcamp.inviobootcampbp_sepette.databinding.FragmentProfileBinding
import com.bootcamp.inviobootcampbp_sepette.ui.activity.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    var auth: FirebaseAuth = Firebase.auth
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.profileFragment = this

        val user = auth.currentUser
        val email = user!!.email.toString()
        binding.emailText = email

    }

    fun resetEmail() {
        auth.currentUser!!.sendEmailVerification()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.e("Reset mail","success")
                    Toast.makeText(requireActivity(), "Email gönderildi.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    fun resetPassword() {
        val user = auth.currentUser
        auth.sendPasswordResetEmail(user!!.email.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.e("Reset password","success")
                    Toast.makeText(requireActivity(), "Email gönderildi.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    fun signOut() {
        auth.signOut()
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempModel:ProfileViewModel by viewModels()
        viewModel = tempModel
    }*/
}