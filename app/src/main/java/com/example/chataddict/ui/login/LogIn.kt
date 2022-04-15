package com.example.chataddict.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.chataddict.R
import com.example.chataddict.databinding.LoginFragmentBinding
import com.google.firebase.auth.FirebaseAuth


class LogIn : Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private lateinit var authenticate: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authenticate = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.login_fragment, container, false)
        binding.LoginButton.setOnClickListener {
            val email = binding.UserName.editText?.text.toString()
            val pass = binding.Password.editText?.text.toString()
            when {
                email.isEmpty() && pass.isEmpty() -> Toast.makeText(
                    requireContext(), "Enter Email And Password 😡", Toast.LENGTH_LONG
                ).show()
                email.isEmpty() && pass.isNotEmpty() -> Toast.makeText(
                    requireContext(), "Enter Email 🧐", Toast.LENGTH_LONG
                ).show()
                email.isNotEmpty() && pass.isEmpty() -> Toast.makeText(requireContext(),"Enter Password 😡"
                    ,Toast.LENGTH_LONG).show()
                email.isNotEmpty() && pass.isNotEmpty() -> authentication(email, pass)
            }
            return@setOnClickListener

        }
        binding.register.setOnClickListener {
            requireView().findNavController().navigate(R.id.action_LogIn_to_register)
            return@setOnClickListener
        }

        return binding.root

    }

    private fun authentication(email: String, pass: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("Authentication","Not authenticated ${it.result}")
                    requireView().findNavController()
                        .navigate(LogInDirections.actionLogInToConversation())
                    Toast.makeText(requireContext(),"WELCOME 😁",Toast.LENGTH_LONG).show()
                }else{Toast.makeText(requireContext(),"Didn't logIn🤔",Toast.LENGTH_LONG).show()}
            }
    }
}