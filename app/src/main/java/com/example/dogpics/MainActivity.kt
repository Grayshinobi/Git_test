package com.example.dogpics

import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogpics.databinding.ActivityMainBinding
import com.example.dogpics.network.DogPhotos
import com.example.dogpics.network.DogPhotosApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private lateinit var binding :ActivityMainBinding

const val BASE_URL="https://dog.ceo/api/breeds/image/random/"
class MainActivity : AppCompatActivity() {
    lateinit var  dogPhotoAdapter:DogPhotoAdapter
    lateinit var  linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
   linearLayoutManager =  LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
      getPhotos()

    }


    private fun getPhotos() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(DogPhotosApiService::class.java)
        val retrofitData = retrofitBuilder.getDogPhotos()

        retrofitData.enqueue(object : Callback<List<DogPhotos>?> {
            override fun onResponse(
                call: Call<List<DogPhotos>?>,
                response: Response<List<DogPhotos>?>
            ) {
                val responseBody = response.body()!!
                dogPhotoAdapter= DogPhotoAdapter(baseContext,responseBody)
                dogPhotoAdapter.notifyDataSetChanged()
                recyclerView.adapter = dogPhotoAdapter

            }

            override fun onFailure(call: Call<List<DogPhotos>?>, t: Throwable) {
                d("Tag", "onFailure" + t.message)
            }
        })

    }
}
