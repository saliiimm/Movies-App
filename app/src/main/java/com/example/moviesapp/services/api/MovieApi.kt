package com.example.moviesapp.services.api

import com.example.moviesapp.BuildConfig
import com.example.moviesapp.models.DiscoverResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieApi {
    @Headers("Authorization: Bearer ${BuildConfig.TMDB_API_KEY}")
    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("include_adult") includeAdult: Boolean = true,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String = "popularity.desc"
    ): DiscoverResponse
}
