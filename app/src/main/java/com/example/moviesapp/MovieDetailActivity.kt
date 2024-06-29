package com.example.moviesapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.moviesapp.libs.RetrofitInstance
import com.example.moviesapp.models.MovieDetailResponse
import com.example.moviesapp.models.ProductionCountry
import com.example.moviesapp.utils.displayCountryFlags
import com.example.moviesapp.utils.formatCurrency
import com.example.moviesapp.utils.loadImage
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var moviePoster: ImageView
    private lateinit var movieBackdrop: ImageView
    private lateinit var movieAdult: ImageView
    private lateinit var movieTitle: TextView
    private lateinit var movieGenres: TextView
    private lateinit var movieOverview: TextView
    private lateinit var movieReleaseDate: TextView
    private lateinit var movieBudget: TextView
    private lateinit var movieRevenue: TextView
    private lateinit var movieVoteAverage: TextView
    private lateinit var countryFlagsContainer: LinearLayout
    private lateinit var movieProductionCompanies: TextView
    private lateinit var detailInfoContainer: NestedScrollView

    private lateinit var toolbar: Toolbar
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_detail)

        initializeViews()
        setupToolbar()
        setupSwipeRefresh()

        val movieId = intent.getIntExtra("MOVIE_ID", -1)
        Log.d("MovieDetailActivity", "Received movie ID: $movieId")

        if (movieId != -1) {
            fetchMovieDetails(movieId)
        } else {
            Log.e("MovieDetailActivity", "Invalid movie ID")
        }
    }

    private fun initializeViews() {
        detailInfoContainer = findViewById(R.id.DetailInfoContainer)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayoutDetail)
        progressBar = findViewById(R.id.progressBar2)
        toolbar = findViewById(R.id.toolbar)

        moviePoster = findViewById(R.id.moviePoster)
        movieBackdrop = findViewById(R.id.movieBackdrop)
        movieTitle = findViewById(R.id.movieTitle)
        movieGenres = findViewById(R.id.movieGenres)
        movieOverview = findViewById(R.id.movieOverview)
        movieReleaseDate = findViewById(R.id.movieReleaseDate)
        movieBudget = findViewById(R.id.movieBudget)
        movieRevenue = findViewById(R.id.movieRevenue)
        movieVoteAverage = findViewById(R.id.movieVoteAverage)
        movieProductionCompanies = findViewById(R.id.movieProductionCompanies)
        movieAdult = findViewById(R.id.adultImageView)
        countryFlagsContainer = findViewById(R.id.countryFlagsContainer)
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = null

        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            val movieId = intent.getIntExtra("MOVIE_ID", -1)
            fetchMovieDetails(movieId)
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun fetchMovieDetails(movieId: Int) {
        showLoading(true)
        lifecycleScope.launch {
            try {
                Log.d("MovieDetailActivity", "Fetching movie details for ID: $movieId")
                val response = RetrofitInstance.detailapi.getMovieDetails(movieId)
                Log.d("MovieDetailActivity", "Movie details fetched successfully")
                populateUI(response)
            } catch (e: Exception) {
                Log.e("MovieDetailActivity", "Error fetching movie details: ${e.message}")
            } finally {
                showLoading(false)
            }
        }
    }

    @SuppressLint("SetTextI18n", "DefaultLocale")
    private fun populateUI(movie: MovieDetailResponse) {
        movieTitle.text = movie.title
        movieOverview.text = movie.overview
        movieReleaseDate.text = " ${movie.release_date}"
        movieBudget.text = " ${formatCurrency(movie.budget)} $"
        movieRevenue.text = " ${formatCurrency(movie.revenue)} $"
        movieVoteAverage.text = " ${String.format("%.1f", movie.vote_average)}"
        movieGenres.text = " ${movie.genres.joinToString { it.name }}"
        movieProductionCompanies.text = " ${movie.production_companies.joinToString { it.name }}"

        movieAdult.setImageResource(if (movie.adult) R.drawable.adult_18_svgrepo_com else R.drawable.child_program_svgrepo_com)

        loadImage(movie.poster_path, moviePoster)
        loadImage(movie.backdrop_path, movieBackdrop)
        displayCountryFlags(this, movie.production_countries, countryFlagsContainer)
    }



    private fun showLoading(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
        detailInfoContainer.visibility = if (show) View.GONE else View.VISIBLE
    }
}
