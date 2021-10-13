package com.example.dogpics

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogpics.network.DogPhotos
import kotlinx.android.synthetic.main.photo_lists.view.*

class DogPhotoAdapter( val context: Context?,  val photoLists: DogPhotos) :
    RecyclerView.Adapter<DogPhotoAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(dogPhoto: String) {
            Glide.with(context!!)
                    .load(dogPhoto)
                    .fitCenter()
                    .placeholder(R.drawable.spinner_test4)
                    .fitCenter()
                    .into(itemView.dogpic)

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.photo_lists, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dogPhoto = photoLists.message[position]
        holder.bind(dogPhoto)
    }


    override fun getItemCount(): Int {
        return photoLists.message.size
    }
}

