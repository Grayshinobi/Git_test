package com.example.dogpics.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.dogpics.R
import com.example.dogpics.adapters.CustomAdapter
import com.example.dogpics.databinding.FragmentUserInputBinding
import kotlinx.android.synthetic.main.fragment_dog_photo.*

private lateinit var binding: FragmentUserInputBinding
private lateinit var viewModel: UserInputViewModel

class UserInput : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[UserInputViewModel::class.java]
        binding = DataBindingUtil.inflate(
            layoutInflater, R.layout.fragment_user_input, container, false
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val amount = binding.spinner1
        val input = binding.spinner2

//        input.onItemSelectedListener = this
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.dog_pics,
            android.R.layout.simple_spinner_item
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            input.adapter = arrayAdapter

            val intList: MutableList<Int> = viewModel.readValue()
            val spinnerAdapter = CustomAdapter(requireContext(), intList)
            amount.adapter = spinnerAdapter

        }



        binding.Button.setOnClickListener {

            val input = input.selectedItem
            println(input)

            val amount = amount.selectedItem
            requireView().findNavController().navigate(
                UserInputDirections.actionUserInputToDogPhotoFragment(
                    input.toString(), amount.toString().toInt()
                )
            )
        }
        return binding.root
    }


}



