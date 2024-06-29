package com.example.moviesapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.MovieDetailActivity
import com.example.moviesapp.R
import com.example.moviesapp.models.Movie
import com.example.moviesapp.utils.formatYearFromDate
import com.example.moviesapp.utils.loadImage

class MovieAdapter(private var movies: List<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.movieTitle)
        val release: TextView = itemView.findViewById(R.id.movieRelease)
        val poster: ImageView = itemView.findViewById(R.id.moviePoster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.title.text = movie.title
        holder.release.text = formatYearFromDate(movie.release_date)

        val posterUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"
        holder.poster.loadImage(posterUrl)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra("MOVIE_ID", movie.id)
            context.startActivity(intent)
        }
    }

    fun addMovies(newMovies: List<Movie>) {
        val currentSize = movies.size
        movies = movies + newMovies
        notifyItemRangeInserted(currentSize, newMovies.size)
    }

    override fun getItemCount() = movies.size
}
