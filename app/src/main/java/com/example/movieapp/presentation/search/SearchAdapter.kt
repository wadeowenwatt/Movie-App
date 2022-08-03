package com.example.movieapp.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieapp.R
import com.example.movieapp.domain.model.Search

class SearchAdapter(private val listFilm: List<Search>) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.search_image)
        val name: TextView = itemView.findViewById(R.id.search_film_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return SearchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val element = listFilm[position]
        val iconUrl = "https://image.tmdb.org/t/p/w500${element.image}"
        val uri = iconUrl.toUri().buildUpon().scheme("https").build()
        holder.image.load(uri)

        holder.name.text = element.name

        holder.image.setOnClickListener {
            it.findNavController()
                .navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment(element.id.toInt()))
        }

    }

    override fun getItemCount(): Int = listFilm.size

}


