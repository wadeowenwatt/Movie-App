package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R

class URViewPagerAdapter(private val imageList: List<Int>) :
    RecyclerView.Adapter<URViewPagerAdapter.URViewHolder>() {
    class URViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.upcoming_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): URViewHolder {
        val itemView = LayoutInflater
            .from(parent.context).inflate(R.layout.item_upcoming, parent, false)
        return URViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: URViewHolder, position: Int) {
        val element = imageList[position]
        holder.imageView.setImageResource(element)
    }

    override fun getItemCount(): Int = 3


}