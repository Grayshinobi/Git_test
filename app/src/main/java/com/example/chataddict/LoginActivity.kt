package com.example.chataddict

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.chataddict.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth



class LoginActivity : AppCompatActivity() {
 private lateinit var binding : ActivityLoginBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_login)

        binding.LoginButton.setOnClickListener {
            val email = binding.UserName.editText?.text.toString()
            val pass = binding.Password.editText?.text.toString()
            when {
                email.isEmpty() && pass.isEmpty() -> Toast.makeText(
                    this, "Enter Email And Password 😡", Toast.LENGTH_LONG
                ).show()
                email.isEmpty() && pass.isNotEmpty() -> Toast.makeText(
                    this, "Enter Email 🧐", Toast.LENGTH_LONG
                ).show()
                email.isNotEmpty() && pass.isEmpty() -> Toast.makeText(
                    this, "Enter Password 😡", Toast.LENGTH_LONG
                ).show()
                email.isNotEmpty() && pass.isNotEmpty() -> authentication(email, pass)
            }
            return@setOnClickListener

        }
        binding.register.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
            return@setOnClickListener
        }
    }

    private fun authentication(email: String, pass: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("Authentication","Not authenticated ${it.result}")

                    val intent = Intent(this, Conversation::class.java)

                    startActivity(intent)

                    Toast.makeText(this,"WELCOME 😁",Toast.LENGTH_LONG).show()
                }else{Toast.makeText(this,"Didn't logIn🤔",Toast.LENGTH_LONG).show()}
            }
    }

}