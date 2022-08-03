package com.example.movieapp.presentation.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieapp.R
import com.example.movieapp.data.remote.dto.detail.Cast

class CastAdapter(private val castList: List<Cast>) :
    RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.cast_image)
        val castName: TextView = itemView.findViewById(R.id.cast_name)
        val characterName: TextView = itemView.findViewById(R.id.character_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image_cast, parent, false)
        return CastViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        val element = castList[position]
        val iconUrl = "https://image.tmdb.org/t/p/w500${element.profile_path}"
        val uri = iconUrl.toUri().buildUpon().scheme("https").build()

        holder.imageView.load(uri) {
            placeholder(R.drawable.placeholder_avatar)
            error(R.drawable.placeholder_avatar)
        }
        holder.castName.text = element.original_name
        holder.characterName.text = element.character
    }

    override fun getItemCount(): Int = castList.size

}