package com.example.dogpics

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogpics.fragments.DogPhotoFragment
import kotlinx.android.synthetic.main.photo_lists.view.*

var limit = 3

class DogPhotoAdapter(private val context: Context, private val photoLists: List<String>) :
    RecyclerView.Adapter<DogPhotoAdapter.ViewHolder>() {
       class ViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {

           fun bind(dogPhoto: String) {
            Glide.with(context)
                    .load(dogPhoto)
                    .fitCenter()
                    .placeholder(R.drawable.loading)
                    .fitCenter()
                    .into(itemView.dogpic)
               }

            fun onClick(itemView: View){
                itemView.setOnClickListener {

                }
            }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.photo_lists, parent, false)
        return ViewHolder(itemView,context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dogPhoto = photoLists[position]
        holder.bind(dogPhoto)
    }


    override fun getItemCount(): Int {
        var limit = 1
        return if (limit < photoLists.size) {
            limit
        }else{photoLists.size}
    }
}


