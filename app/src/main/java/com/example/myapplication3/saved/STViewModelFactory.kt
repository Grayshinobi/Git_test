package com.example.myapplication3.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class STViewModelFactory(private val args: SavedTextArgs) :
    ViewModelProvider.Factory {
    @Suppress("args")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                 return SavedTextViewModel(args)  as T
        }

    }
