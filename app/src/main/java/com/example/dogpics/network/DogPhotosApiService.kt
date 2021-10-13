package com.example.dogpics.network

import retrofit2.Call
import retrofit2.http.GET

interface DogPhotosApiService {
     @GET("50")
    fun getDogPhotos(): Call<DogPhotos>
}