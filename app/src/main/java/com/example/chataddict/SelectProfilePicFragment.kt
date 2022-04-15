package com.example.chataddict

import android.app.Activity
import android.content.Intent
import android.content.Intent.ACTION_PICK
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.chataddict.databinding.FragmentSelectProfilePicBinding


class SelectProfilePicFragment : Fragment() {

    private lateinit var binding : FragmentSelectProfilePicBinding
    private var selectedImage: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        SelectImgae()
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_select_profile_pic,container,false)
        return binding.root
    }

    private fun SelectImgae() {

        val selectImage = binding.profilePic
        selectImage.setOnClickListener{
            val intent = Intent(ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent,0)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && requestCode == Activity.RESULT_OK && data !== null){
           selectedImage = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver,selectedImage)
            val bitMapDrawable = BitmapDrawable(bitmap)
            binding.profilePic.setBackgroundDrawable(bitMapDrawable)

        }
    }


}