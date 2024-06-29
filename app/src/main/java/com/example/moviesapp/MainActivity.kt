package com.example.moviesapp

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.moviesapp.adapters.MovieAdapter
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.libs.RetrofitInstance
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var progressBarTop: ProgressBar
    private lateinit var progressBarBottom: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private var currentPage = 1
    private var isFetching = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setupListeners()

        fetchMovies()
    }

    private fun setupViews() {
        swipeRefreshLayout = binding.swipeRefreshLayout
        progressBarTop = binding.progressBarTop
        progressBarBottom = binding.progressBarBottom
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        movieAdapter = MovieAdapter(emptyList())
        recyclerView.adapter = movieAdapter
    }

    private fun setupListeners() {
        recyclerView.addOnScrollListener(createOnScrollListener())
        swipeRefreshLayout.setOnRefreshListener {
            refreshMovies()
        }
    }

    private fun createOnScrollListener(): RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (!isFetching && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
                    currentPage++
                    fetchMovies()
                }
            }
        }
    }

    private fun refreshMovies() {
        currentPage = 1
        movieAdapter = MovieAdapter(emptyList())
        recyclerView.adapter = movieAdapter
        fetchMovies()
        swipeRefreshLayout.isRefreshing = false
    }

    private fun fetchMovies() {
        showLoading(currentPage == 1, true)
        isFetching = true
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.discoverMovies(page = currentPage)
                movieAdapter.addMovies(response.results)
            } catch (e: Exception) {
            } finally {
                showLoading(currentPage == 1, false)
                isFetching = false
            }
        }
    }

    private fun showLoading(isTop: Boolean, show: Boolean) {
        if (isTop) {
            progressBarTop.visibility = if (show) View.VISIBLE else View.GONE
        } else {
            progressBarBottom.visibility = if (show) View.VISIBLE else View.GONE
        }
    }
}
