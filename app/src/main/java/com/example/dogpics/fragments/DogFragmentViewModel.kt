package com.example.dogpics.fragments

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogpics.DogPhotoAdapter
import com.example.dogpics.network.DogPhotos
import com.example.dogpics.network.DogPhotosApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://dog.ceo/api/breeds/image/random/"

class DogFragmentViewModel : ViewModel(){
    private lateinit var dogPhotoAdapter: DogPhotoAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    fun getPhotos(context: Context?, recyclerView: RecyclerView) {

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

                        dogPhotoAdapter = DogPhotoAdapter(context!!, responseBody.message)
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
    fun setRecyclerView(context: Context?, recyclerView: RecyclerView ){

        recyclerView.setHasFixedSize(false)
        linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager


    }
}
