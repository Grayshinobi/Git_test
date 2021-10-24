package com.example.dogpics.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.dogpics.R
import com.example.dogpics.databinding.FragmentUserInputBinding
import kotlinx.android.synthetic.main.fragment_dog_photo.*
import kotlinx.android.synthetic.main.fragment_user_input.*

private lateinit var binding : FragmentUserInputBinding

class UserInput : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            layoutInflater, R.layout.fragment_user_input, container, false
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val input: EditText = text_input
//        val limit = input.text.toString().toInt()

        binding.Button.setOnClickListener {
//            println(limit)
            requireView().findNavController().navigate(UserInputDirections.actionUserInputToDogPhotoFragment())
        }
        return binding.root
    }
}


