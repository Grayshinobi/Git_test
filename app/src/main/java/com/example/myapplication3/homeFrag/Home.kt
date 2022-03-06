package com.example.myapplication3.homeFrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.myapplication3.R
import com.example.myapplication3.databinding.FragmentHomeBinding


class Home : Fragment() {
    private lateinit var binding : FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_home,container,false)
        val title : EditText = binding.editTextTask
        val description : EditText = binding.editTextDesc
        val button : Button = binding.buttonSave



        button.setOnClickListener { view ->
            Toast.makeText(context,"button Clicked",Toast.LENGTH_SHORT).show()
            view.findNavController().navigate(HomeDirections.actionHomeToSavedText(title.toString(), description.toString() ))
        }
        return binding.root
    }


}