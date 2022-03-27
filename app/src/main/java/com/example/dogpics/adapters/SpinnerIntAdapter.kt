package com.example.dogpics.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.dogpics.R


class CustomAdapter(context: Context, private var source: MutableList<Int>) : BaseAdapter() {
    private val infalater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    class ItemHolder(row: View) {
        val nums: TextView = row.findViewById(R.id.amount) as TextView

    }

    override fun getCount(): Int {
        return source.size
    }

    override fun getItem(position: Int): Any {
        return source[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val itemHolder: ItemHolder
        if (convertView == null) {
            view = infalater.inflate(R.layout.spinner_item_placeholder, parent, false)
            itemHolder = ItemHolder(view)
            view?.tag = itemHolder
        } else {
            view = convertView
            itemHolder = view.tag as ItemHolder
        }
        itemHolder.nums.text = source[position].toString()
        return view
    }


}