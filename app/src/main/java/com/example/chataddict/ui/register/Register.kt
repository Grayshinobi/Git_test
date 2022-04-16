package com.example.chataddict.ui.register

import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.chataddict.R
import com.example.chataddict.databinding.FragmentRegisterBinding
import com.example.chataddict.ui.dataBase.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.*

class Register : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private var imageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_register, container, false)
        selectImage()
        binding.RegisterButton.setOnClickListener {
            val email = binding.regEmail.editText?.text.toString()
            val pass = binding.regPass.editText?.text.toString()


            when {
                email.isEmpty() && pass.isEmpty() -> {
                    Toast.makeText(requireContext(), "Enter Email And PassWord", Toast.LENGTH_LONG)
                        .show()
                }
                email.isEmpty() && pass.isNotEmpty() -> {
                    Toast.makeText(requireContext(), "Enter Email Address", Toast.LENGTH_LONG)
                        .show()
                }
                email.isNotEmpty() && pass.isEmpty() -> {
                    Toast.makeText(requireContext(), "Enter Email Password", Toast.LENGTH_LONG)
                        .show()
                }
                email.isNotEmpty() && pass.length < 8 -> {
                    Toast.makeText(
                        requireContext(),
                        "password is less than 8 🤨",
                        Toast.LENGTH_LONG
                    ).show()
                }
                email.isNotEmpty() && pass.isNotEmpty() -> {
                    registering(email, pass)
                }
            }
            return@setOnClickListener


        }
        return binding.root
    }

    @Suppress("DEPRECATION")
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
        if (requestCode == 0) {
            Log.d("Registration", "Image was selected $requestCode")
            imageUri = data?.data
            val bitmap =
                MediaStore.Images.Media.getBitmap(requireContext().contentResolver, imageUri)
            val bitMapDrawable = BitmapDrawable(bitmap)
            binding.profilePic.setBackgroundDrawable(bitMapDrawable)
        }
    }

    private fun registering(email: String, pass: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                printToast(context)
                Log.d("Registration", " User successfully registered!!!")
                Log.d("Registration", "email: $email + password: $pass")

                uploadImageToFirebase()
                requireView().findNavController().navigate(R.id.action_register_to_LogIn)

            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Not registered !!!", Toast.LENGTH_LONG)
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
        val storage = Firebase.storage
        val storageRef = storage.reference.child("images/$fileName")

        storageRef.putFile(imageUri!!).addOnSuccessListener {
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


    private fun saveUserToFirebase(profileImgUrl: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val reference = FirebaseDatabase.getInstance().getReference("/Users/$uid")
        val email = binding.regEmail.editText?.text.toString()
        val user = User(uid, email, profileImgUrl)
        reference.setValue(user)
            .addOnSuccessListener {
                Log.d("Registration", "User saved to firebase")
            }
            .addOnFailureListener {
                Log.d("Registration", "User not save to firebase : ${it.cause}")
            }
    }
}




