package com.example.moviesapp.models

data class DiscoverResponse(
    val page: Int,
    val results: List<Movie>
)
