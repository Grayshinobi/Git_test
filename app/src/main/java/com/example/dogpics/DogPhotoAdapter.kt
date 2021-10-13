package com.example.dogpics

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogpics.network.DogPhotos
import kotlinx.android.synthetic.main.photo_lists.view.*

class DogPhotoAdapter(val context: Context , val photoList:List<DogPhotos>): RecyclerView.Adapter<DogPhotoAdapter.ViewHolder>(){
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){


init {
    itemView.text1
}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.photo_lists,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
     holder.itemView.text1.text=photoList[position].status.toString()

    }

    override fun getItemCount(): Int {
        return photoList.size
    }
}