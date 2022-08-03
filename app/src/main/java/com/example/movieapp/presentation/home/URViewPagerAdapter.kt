package com.example.movieapp.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieapp.R
import com.example.movieapp.domain.model.ItemUpcoming

class URViewPagerAdapter(private val imageList: List<ItemUpcoming>) :
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
        val iconUrl = "https://image.tmdb.org/t/p/w500${element.image}"
        val uri = iconUrl.toUri().buildUpon().scheme("https").build()
        holder.imageView.load(uri)
        holder.imageView.setOnClickListener {
            it.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(element.id.toInt()))
        }
    }

    override fun getItemCount(): Int = imageList.size

}