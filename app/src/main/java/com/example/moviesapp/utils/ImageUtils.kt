package com.example.moviesapp.utils


import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImage(url: String) {
    Picasso.get().load(url).into(this)
}

fun loadImage(path: String?, imageView: ImageView) {
    path?.let {
        val url = "https://image.tmdb.org/t/p/w500$it"
        Picasso.get().load(url).into(imageView)
    }
}

