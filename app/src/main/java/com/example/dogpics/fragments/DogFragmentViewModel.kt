package com.example.dogpics.fragments

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogpics.dataBase.PicDataBase
import com.example.dogpics.network.DogPhotos
import com.example.dogpics.network.DogPhotosApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://dog.ceo/api/"

class DogFragmentViewModel(
    args: DogPhotoFragmentArgs
) : ViewModel() {

    private lateinit var linearLayoutManager: LinearLayoutManager

    lateinit var toastCallback: OnToastCallback

    private var dataBase = PicDataBase


    val listOfPictures = MutableLiveData<List<String>>()

    private val retrofitBuilder: DogPhotosApiService = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(DogPhotosApiService::class.java)


    private val input = args.input
    private val amount = args.amount
    private val akita = "akita"
    private val australian = "australian"
    private val bulldog = "bulldog"
    private val boxer = "boxer"
    private val breed = listOf("All Type", "Akita", "Australian", "Bull Dog", "Boxer")

    init {

    }

    private fun getSpecificDogBreedService(
        type: String,
        amount: Int
    ): Call<DogPhotos> {
        return retrofitBuilder.getDogBreedPhotos(type, amount)
    }

    fun choose(context: Context) {
        val callObject: Call<DogPhotos> = when (input) {
            breed[0] -> getAllTypePhotos(amount)

            breed[1] -> getSpecificDogBreedService(akita, amount)

            breed[2] -> getSpecificDogBreedService(australian, amount)

            breed[3] -> getSpecificDogBreedService(bulldog, amount)

            breed[4] -> getSpecificDogBreedService(boxer, amount)

            else -> getAllTypePhotos(amount)
        }


        callObject.enqueue(object : Callback<DogPhotos?> {

            override fun onResponse(call: Call<DogPhotos?>, response: Response<DogPhotos?>) {
                if (response.isSuccessful) {
                    val photoListFrag = PhotoList()
                    photoListFrag.photoList?.loading?.visibility = View.GONE
                    val responseBody: DogPhotos = response.body()!!

                    listOfPictures.value = responseBody.message
                    if (response.body() != null) {
                        dataBase.getDataBase(context = context).photosDao()
                    }
//                    insertToDatabase(input,responseBody.message)
                    if (this@DogFragmentViewModel::toastCallback.isInitialized) {
                        toastCallback.sendToast("photos ready")
                    }


                } else {


                    if (this@DogFragmentViewModel::toastCallback.isInitialized) {
                        toastCallback.sendToast("Api failures")
                    }

                }
            }

            override fun onFailure(call: Call<DogPhotos?>, t: Throwable) {
                if (this@DogFragmentViewModel::toastCallback.isInitialized) {
                    toastCallback.sendToast("Loading fail ")
                }
            }
        })

    }


    private fun getAllTypePhotos(amount: Int): Call<DogPhotos> {
        return retrofitBuilder.getAllDogPhotos(amount)
    }

    fun setRecyclerView(context: Context?, recyclerView: RecyclerView) {

        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager

    }
}

interface OnToastCallback {
    fun sendToast(toastMessage: String)
}