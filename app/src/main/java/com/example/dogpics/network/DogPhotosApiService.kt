package com.example.dogpics.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DogPhotosApiService {
    @GET("breed/{type}/images/random/{amount}")
    fun getDogBreedPhotos(
        @Path("type") type: String, @Path("amount") amount: Int
    ): Call<DogPhotos>

    @GET("breeds/image/random/{amount}")
    fun getAllDogPhotos(@Path("amount") amount: Int): Call<DogPhotos>


}