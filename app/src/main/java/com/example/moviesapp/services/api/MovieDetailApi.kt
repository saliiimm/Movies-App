package com.example.moviesapp.services.api

import com.example.moviesapp.models.MovieDetailResponse
import com.example.moviesapp.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieDetailApi {
    @Headers("Authorization: Bearer ${BuildConfig.TMDB_API_KEY}")
    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int,
        @Query("language") language: String = "en-US"
    ): MovieDetailResponse
}
