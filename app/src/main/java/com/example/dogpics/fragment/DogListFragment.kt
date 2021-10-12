package com.example.dogpics.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.dogpics.R
import com.example.dogpics.databinding.FragmentDogListBinding
import com.example.dogpics.databinding.GridViewItemBinding
import com.example.dogpics.network.DogPhotos
import com.example.dogpics.network.DogPhotosApiService
import kotlinx.android.synthetic.main.grid_view_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://dog.ceo/api/breed/hound/"

class DogListFragment : Fragment() {

    private lateinit var binding: FragmentDogListBinding
    private lateinit var viewModel: DogListFragmentModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(DogListFragmentModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dog_list, container, false)
        binding.button.setOnClickListener {
            binding.button.visibility = View.GONE
            binding.textId.visibility = View.VISIBLE
        }
        getPhotos()
        return binding.root
    }

 fun getPhotos() {
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
                val myStringBuilder = StringBuilder()

                    for (DogPhotos in responseBody) {
                        myStringBuilder.append(DogPhotos.message)
                        myStringBuilder.append("\n")
                    }
                textId.text= myStringBuilder
            }

            override fun onFailure(call: Call<List<DogPhotos>?>, t: Throwable)
            {
                Log.d("Tag", "onFailure" + t.message)
            }
        })


    }
}




