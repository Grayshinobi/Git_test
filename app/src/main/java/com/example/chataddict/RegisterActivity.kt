package com.example.chataddict

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.chataddict.databinding.ActivityRegisterBinding
import com.example.chataddict.ui.dataBase.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.RegisterButton.setOnClickListener {
            selectImage()
            val email = binding.regEmail.editText?.text.toString()
            val pass = binding.regPass.editText?.text.toString()

            when {
                email.isEmpty() && pass.isEmpty() -> {
                    Toast.makeText(this, "Enter user name,Email And PassWord", Toast.LENGTH_LONG)
                        .show()
                }
                email.isEmpty() && pass.isNotEmpty() -> {
                    Toast.makeText(this, "Enter Email Address", Toast.LENGTH_LONG)
                        .show()
                }
                email.isNotEmpty() && pass.isEmpty() -> {
                    Toast.makeText(this, "Enter Email Password", Toast.LENGTH_LONG)
                        .show()
                }
                email.isNotEmpty() && pass.length < 8 -> {
                    Toast.makeText(
                       this,
                        "password is less than 8 🤨",
                        Toast.LENGTH_LONG
                    ).show()
                }
                email.isNotEmpty() && pass.isNotEmpty() -> {
                    registering(email, pass)
                }
            }
            @Suppress("DEPRECATION")
            return@setOnClickListener


        }
    }



    private fun selectImage() {

        val selectImage = binding.profilePic
        selectImage.setOnClickListener {
            Log.d("Registration", "show image selector")
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)

        }
    }

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0  && requestCode == Activity.RESULT_OK ) {
            Log.d("Registration", "Image was selected $requestCode")
            imageUri = data?.data
            Log.d("Registration", "what is :- ${data?.data}")
            val bitmap =
                MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
            binding.circleImageView.setImageBitmap(bitmap)
            binding.profilePic.alpha = 0f


        }
    }

    private fun registering(email: String, pass: String) {
        val authentication = FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
        authentication.addOnCompleteListener {
            if (!it.isSuccessful) return@addOnCompleteListener

            printToast(this)
            Log.d("Registration", " User successfully registered!!!")
            Log.d("Registration", "email: $email + password: $pass")

            uploadImageToFirebase()
            val intent = Intent(this,Conversation::class.java)
            startActivity(intent)
        }
            .addOnFailureListener {
                Toast.makeText(this, "Not registered !!!", Toast.LENGTH_LONG)
                    .show()
            }

    }

    private fun printToast(context: Context?) {

        Toast.makeText(context, "registered Successful!!!", Toast.LENGTH_LONG).show()

        Handler(Looper.getMainLooper()).postDelayed({
            Toast.makeText(
                context,
                "LogIn using registered account 😎",
                Toast.LENGTH_LONG
            )
                .show()
        }, 3000)

    }

    private fun uploadImageToFirebase() {
        Log.d("Registration", "Upload_function accessed")
        val fileName = UUID.randomUUID().toString()
        val storageRef = Firebase.storage.reference.child("images/$fileName")

        imageUri?.let {
            storageRef.putFile(it)
                .addOnSuccessListener {
                Log.d("Registration", "Image uploaded successfully")
                storageRef.downloadUrl.addOnSuccessListener {
                    Log.d("Registration", "File Location : $it")

                    saveUserToFirebase(it.toString())
                }
            }
                .addOnFailureListener {
                    Log.d("Registration", "Image not uploaded !!!: ${it.cause}")
                }
        }

    }

    private fun saveUserToFirebase(profileImgUrl: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        //according to the latest documentation
        val dataBaseRef = Firebase.database.reference.child("/Users/$uid")
        //deprecated javaDoc
//        val reference = FirebaseDatabase.getInstance().getReference("/Users/$uid")
        val email = binding.regEmail.editText?.text.toString()
        val userName = binding.regUserName.editText?.text.toString()
        val user = User(uid, userName, email, profileImgUrl)
        dataBaseRef.setValue(user)
            .addOnSuccessListener {
                Log.d("Registration", "userName:$userName email:$email ")
                Log.d("Registration", "User saved to firebase Database")

                val intent = Intent(this, Conversation::class.java)
                intent.type =
                    Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK).toString()
                startActivity(intent)
            }
            .addOnFailureListener {
                Log.d("Registration", "User not save to firebase Database: ${it.cause}")
            }
    }
}