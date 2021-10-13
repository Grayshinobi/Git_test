package com.example.dogpics

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogpics.network.DogPhotos
import kotlinx.android.synthetic.main.photo_lists.view.*

class DogPhotoAdapter(private val context: Context, private val photoList:List<DogPhotos>): RecyclerView.Adapter<DogPhotoAdapter.ViewHolder>(){
   inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
     var pics :ImageView

init {
     pics = itemView.dogpic
}
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.photo_lists,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      Glide.with(context).load(photoList).into(holder.pics)

    }


    override fun getItemCount(): Int {
        return photoList.size
    }
}