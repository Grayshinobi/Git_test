package com.example.chataddict.ui.register

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
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class Register : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private var ImageUri: Uri? = null

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
            Log.d("Registration","show image selector")
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,0)


        }
    }

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            Log.d("Registration" ,"Image was selected $requestCode")
            ImageUri = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, ImageUri)
            val bitMapDrawable = BitmapDrawable(bitmap)
            binding.profilePic.setBackgroundDrawable(bitMapDrawable)
        }
    }

    private fun registering(email: String, pass: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    printToast()
                    Log.d("Registration"," User successfully registered!!!")
                    Log.d("Registration","email: ${email} + password: $pass")
                    uploadImageToFirebase()
                    requireView().findNavController().navigate(R.id.action_register_to_LogIn)
                } else {
                    Toast.makeText(requireContext(), "Not registered !!!", Toast.LENGTH_LONG).show()
                }
                return@addOnCompleteListener
            }
    }


    private fun printToast() {


            Toast.makeText(requireContext(), "registered Successful!!!", Toast.LENGTH_LONG)
                .show()


        Handler(Looper.getMainLooper()).postDelayed ({ Toast.makeText(requireContext(), "LogIn using registered account 😎", Toast.LENGTH_LONG)
                .show()
            },3000)

    }

    private fun uploadImageToFirebase() {
        if (ImageUri == null) return
            val fileName = UUID.randomUUID().toString()
            val reference = FirebaseStorage.getInstance().getReference("/images/${fileName}")
            ImageUri?.let { it ->
                reference.putFile(it)
                    .addOnSuccessListener {
                        Log.d("Registration", "Image successfully uploaded : ${it.metadata?.path}")

                        reference.downloadUrl.addOnSuccessListener {
                            Log.d("Registration", "File location : ${it}")
                        }
                        saveUserToFirebase(it.toString())
                    }
            }
        }


    private fun saveUserToFirebase(profileImgUrl: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val reference = FirebaseDatabase.getInstance().getReference("/Users/$uid")
        val email = binding.regEmail.editText?.text.toString()
        val user = User(uid, email, profileImgUrl)
        reference.setValue(user)
            .addOnCompleteListener {
                Log.d("Registration", "user completely registered!!!")
            }
    }


}
