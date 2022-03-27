package com.example.dogpics.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DogFragmentViewModelFactory(

    private var args: DogPhotoFragmentArgs

) : ViewModelProvider.Factory {
    @Suppress("args")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DogFragmentViewModel(args) as T
    }

}