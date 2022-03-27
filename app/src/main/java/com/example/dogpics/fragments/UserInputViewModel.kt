package com.example.dogpics.fragments

import androidx.lifecycle.ViewModel

class UserInputViewModel : ViewModel() {

    fun readValue(): MutableList<Int> {
        val numbers: MutableList<Int> = mutableListOf()
        for (i in 1..50) {
            numbers.add(i)
        }

        return numbers
    }

}