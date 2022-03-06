package com.example.myapplication3.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.myapplication3.R
import com.example.myapplication3.databinding.FragmentSavedTextBinding


class SavedText : Fragment() {
    private lateinit var binding: FragmentSavedTextBinding
    private val args: SavedTextArgs by navArgs()
    private lateinit var viewModel: SavedTextViewModel
    private lateinit var viewModelFactory: STViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelFactory = STViewModelFactory(args)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SavedTextViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_saved_text, container, false)
        val tit = binding.title
        val desc = binding.description
        viewModel.display(
            args,
            tit,
            desc
        )

        return binding.root
    }
}
