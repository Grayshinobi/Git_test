package com.example.dogpics.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.dogpics.R
import com.example.dogpics.adapters.PhotoCardAdapter
import com.example.dogpics.databinding.FragmentDogPhotoBinding
import kotlinx.android.synthetic.main.fragment_dog_photo.*


class DogPhotoFragment : Fragment(), OnToastCallback, PhotoCardAdapter.OnItemClickListener {

    private lateinit var binding: FragmentDogPhotoBinding
    private lateinit var viewModel: DogFragmentViewModel

    private val args: DogPhotoFragmentArgs by navArgs()
    private lateinit var dogPhotoFragmentFactory: DogFragmentViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        dogPhotoFragmentFactory = DogFragmentViewModelFactory(
            args

        )


        viewModel =
            ViewModelProvider(this, dogPhotoFragmentFactory)[DogFragmentViewModel::class.java]

        viewModel.toastCallback = this
        viewModel.choose(requireContext())
        binding =
            DataBindingUtil.inflate(
                layoutInflater, R.layout.fragment_dog_photo, container, false
            )


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        println("onCreative")


        val recyclerView = binding.recyclerView


        viewModel.setRecyclerView(context, recyclerView)



        viewModel.listOfPictures.observe(viewLifecycleOwner) {


            val photoCardAdapter =
                PhotoCardAdapter(requireContext(), photoLists = it, listenerCallBack = this)
            recyclerView.adapter = photoCardAdapter
            println(photoCardAdapter.itemCount)

        }

        return binding.root
    }


    override fun sendToast(toastMessage: String) {
        Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_LONG).show()
    }

    override fun onItemClick(position: Int, context: Context?, photoLists: List<String>) {

        val customDialog = CustomDialog(position, photoLists, requireContext())
        customDialog.show(childFragmentManager, "dialog")
        print("$position")
        Toast.makeText(context, "${position + 1}", Toast.LENGTH_LONG).show()


    }


}