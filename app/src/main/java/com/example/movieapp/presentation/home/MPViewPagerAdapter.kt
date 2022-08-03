package com.example.movieapp.presentation.home
import android.content.Context
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
import com.example.movieapp.domain.model.ItemMostPopular

class MPViewPagerAdapter(
    private val context: Context,
    private val imageList: List<ItemMostPopular>
) :
    RecyclerView.Adapter<MPViewPagerAdapter.MPViewHolder>() {
    class MPViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.most_popular_image)
        val filmName: TextView = itemView.findViewById(R.id.most_popular_film_name)
        val imdb: TextView = itemView.findViewById(R.id.most_popular_imdb)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MPViewHolder {
        val itemView = LayoutInflater
            .from(parent.context).inflate(R.layout.item_most_popular, parent, false)
        return MPViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MPViewHolder, position: Int) {
        val element = imageList[position]
        val iconUrl = "https://image.tmdb.org/t/p/w500${element.image}"
        val uri = iconUrl.toUri().buildUpon().scheme("https").build()
        holder.imageView.load(uri)
        holder.filmName.text = element.name
        holder.imdb.text = context.getString(R.string.imdb_most_popular, element.imdb)
        holder.imageView.setOnClickListener {
            it.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(element.id.toInt()))
        }
    }

    override fun getItemCount(): Int = imageList.size

}