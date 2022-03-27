package com.example.dogpics.fragments

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.dogpics.R
import com.example.dogpics.databinding.PhotoListsBinding


class PhotoList : Fragment() {

    private val _photoList: PhotoListsBinding?
        get() {
            return photoList
        }
    var photoList = _photoList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photoList = DataBindingUtil.setContentView(requireActivity(), R.layout.photo_lists)
    }
}