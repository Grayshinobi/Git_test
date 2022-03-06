package com.example.myapplication3.saved

import android.widget.EditText
import androidx.lifecycle.ViewModel

class SavedTextViewModel(args: SavedTextArgs) : ViewModel()  {

    init{
        args.title
        args.discription
    }

    fun display(args: SavedTextArgs, tit: EditText, desc: EditText) {
        tit.setText(args.title.toString())
        desc.setText(args.discription.toString())
    }

}

