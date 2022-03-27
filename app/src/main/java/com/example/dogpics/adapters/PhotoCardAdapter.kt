package com.example.dogpics.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogpics.R
import kotlinx.android.synthetic.main.photo_lists.view.*


class PhotoCardAdapter(
    private val context: Context,
    private val photoLists: List<String>,
    private var listenerCallBack: OnItemClickListener
) :
    RecyclerView.Adapter<PhotoCardAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        fun bind(bullMastiffPhoto: String) {
            Glide.with(context)
                .load(bullMastiffPhoto)
                .fitCenter()
                .into(itemView.dogpic)
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            itemView.setOnClickListener {

                listenerCallBack.onItemClick(bindingAdapterPosition, context, photoLists)


            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.photo_lists, parent, false)

        return ViewHolder(itemView, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bullMastiffPhoto = photoLists[position]
        holder.bind(bullMastiffPhoto)
    }


    override fun getItemCount(): Int {
        return photoLists.size
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, context: Context?, photoLists: List<String>)

    }
}


