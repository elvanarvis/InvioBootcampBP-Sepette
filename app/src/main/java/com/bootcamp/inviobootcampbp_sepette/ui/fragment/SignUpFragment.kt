package com.bootcamp.inviobootcampbp_sepette.ui.fragment

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
import com.bootcamp.inviobootcampbp_sepette.databinding.FragmentSignUpBinding
import com.bootcamp.inviobootcampbp_sepette.ui.activity.MainActivity
import com.bootcamp.inviobootcampbp_sepette.utils.changePage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DataBindingUtil.bind(view)!!
        binding.signUpFragment = this
        auth = Firebase.auth

        binding.navText.setOnClickListener {
            Navigation.changePage(it, R.id.action_signUpFragment_to_signInFragment)
        }

    }

    fun signUp(email: String, password: String) {

        if(email.isNotEmpty() && password.isNotEmpty()){

            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                val user = auth.currentUser
                val intent = Intent(activity, MainActivity::class.java)
                startActivity(intent)
                activity?.finish()

            }.addOnFailureListener {
                Toast.makeText(requireActivity(), it.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }else
            Toast.makeText(requireActivity(), "e-mail veya şifre boş", Toast.LENGTH_SHORT).show()
    }
}

