package com.example.dogpics.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dogpics.R
import com.example.dogpics.databinding.FragmentDogPhotoBinding
import kotlinx.android.synthetic.main.fragment_dog_photo.*


class DogPhotoFragment : Fragment() {

    private lateinit var binding: FragmentDogPhotoBinding
    private lateinit var viewModel: DogFragmentViewModel
//    val args : DogPhotoFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel = ViewModelProvider(this).get(
            DogFragmentViewModel::class.java
        )
        binding =
            DataBindingUtil.inflate(
                layoutInflater, R.layout.fragment_dog_photo, container, false
            )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val context = context
        val recyclerView =binding.recyclerView
//        val limit = args.limit
        val limit = 5
        viewModel.setRecyclerView(context,recyclerView)


        viewModel.getPhotos(context,recyclerView)
        return binding.root
    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val context = context
        val recyclerView =binding.recyclerView
//        val limit = args.limit
        outState.putString("getPhotos", viewModel.getPhotos(context,recyclerView).toString())

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val context = context
        val recyclerView =binding.recyclerView
//        val limit = args.limit
        savedInstanceState?.getString("getPhotos", viewModel.getPhotos(context,recyclerView).toString())

    }
}