package com.example.dogpics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.dogpics.databinding.FragmentUserInputBinding

class UserInput : Fragment() {
    private lateinit var binding: FragmentUserInputBinding
    private lateinit var viewModel: UserInputViewModel
    private lateinit var input : EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_input, container, false)



         input = binding.textInput
        val actualInput = input.text

        binding.inputButton.setOnClickListener{
            viewModel.userInput(actualInput)
        }
        return binding.root
    }

}