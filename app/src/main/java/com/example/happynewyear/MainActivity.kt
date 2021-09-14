package com.example.happynewyear

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.happynewyear.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        Toast.makeText(this, "Coded by Greyshinobi2013", Toast.LENGTH_LONG).show();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.Done.setOnClickListener {
            addContent(it)
        }


    }

    private fun addContent(view: View) {
        val name = binding.FistInput
        var textInput = binding.wish.text



        binding.wish.text = "To ${name.text} and  your family!!!"


        binding.welcome.visibility = View.GONE
        binding.FistInput.visibility = View.GONE
        binding.Done.visibility = View.GONE
        binding.Gif.visibility = View.VISIBLE
        binding.wish.visibility = View.VISIBLE
        binding.Gif2.visibility = View.VISIBLE

        // Hide the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)


    }
}
