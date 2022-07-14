package com.example.movieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.example.movieapp.R

class MPViewPagerAdapter(private val imageList: List<Int>) :
    RecyclerView.Adapter<MPViewPagerAdapter.MPViewHolder>() {
    class MPViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.most_popular_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MPViewHolder {
        val itemView = LayoutInflater
            .from(parent.context).inflate(R.layout.item_most_popular, parent, false)
        return MPViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MPViewHolder, position: Int) {
        val element = imageList[position]
        holder.imageView.setImageResource(element)
    }

    override fun getItemCount(): Int = 3


}