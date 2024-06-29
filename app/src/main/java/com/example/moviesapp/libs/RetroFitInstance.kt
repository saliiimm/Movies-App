package com.example.moviesapp.libs

import com.example.moviesapp.services.api.MovieDetailApi
import com.example.moviesapp.services.api.MovieApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    val api: MovieApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    val detailapi: MovieDetailApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieDetailApi::class.java)
    }
}
