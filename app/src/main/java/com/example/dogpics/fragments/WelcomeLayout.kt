package com.example.dogpics.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.dogpics.R
import com.example.dogpics.databinding.FragmentWelcomeLayoutBinding
import kotlinx.android.synthetic.main.fragment_dog_photo.*


class WelcomeLayout : Fragment() {
private lateinit var binding : FragmentWelcomeLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_welcome_layout,container,false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
      binding.proceed.setOnClickListener {
          requireView().findNavController().navigate(WelcomeLayoutDirections.actionWelcomeLayoutToUserInput())
      }
        return binding.root
    }

}