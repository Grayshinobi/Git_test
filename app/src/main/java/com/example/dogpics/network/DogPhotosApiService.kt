package com.example.dogpics.network

import retrofit2.Call
import retrofit2.http.GET

interface DogPhotosApiService {
     @GET("3")
    fun getDogPhotos(): Call<List<DogPhotos>>
}