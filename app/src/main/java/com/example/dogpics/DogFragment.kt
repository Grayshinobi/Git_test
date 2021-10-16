package com.example.dogpics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogpics.databinding.FragmentDogBinding
import com.example.dogpics.network.DogPhotos
import com.example.dogpics.network.DogPhotosApiService
import kotlinx.android.synthetic.main.fragment_dog.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://dog.ceo/api/breeds/image/random/"
private lateinit var binding:FragmentDogBinding

class DogFragment : Fragment() {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var dogPhotoAdapter:DogPhotoAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_dog,container,false)
        recyclerView.setHasFixedSize(false)
        linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        recyclerView.layoutManager = linearLayoutManager
        getPhotos()
        return binding.root
    }
    private fun getPhotos() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(DogPhotosApiService::class.java)
        val retrofitData = retrofitBuilder.getDogPhotos()
        println("get photos calle")
        retrofitData.enqueue(object : Callback<DogPhotos?> {
            override fun onResponse(call: Call<DogPhotos?>, response: Response<DogPhotos?>) {
                if (response.isSuccessful) {


                    val responseBody = response.body()!!
                    println("response $responseBody")
                    dogPhotoAdapter = DogPhotoAdapter(context!!,responseBody)
                    recyclerView.adapter = dogPhotoAdapter
                } else {
                    Toast.makeText(context, "Api failures", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<DogPhotos?>, t: Throwable) {
                Toast.makeText(context, "Loading failure", Toast.LENGTH_LONG).show()
            }


        })
    }
}




