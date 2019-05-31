package com.arctouch.codechallenge.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.util.MovieImageUrlBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val MOVIE_DETAIL: String = "MOVIE_DETAIL"
    }

    lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        movie = intent.extras.get(MOVIE_DETAIL) as Movie
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initializeViews()
    }

    private fun initializeViews() {
        findViewById<TextView>(R.id.detail_title_text).text = movie.title
        findViewById<TextView>(R.id.detail_genres_text).text = movie.genres?.joinToString(separator = ", ") { it.name }
        findViewById<TextView>(R.id.detail_overview_text).text = movie.overview
        findViewById<TextView>(R.id.detail_release_text).text = movie.releaseDate

        var posterImage = findViewById<ImageView>(R.id.detail_poster_image)
        var backdropImage = findViewById<ImageView>(R.id.detail_backdrop_image)

        Glide.with(posterImage)
                .load(movie.posterPath?.let { MovieImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                .into(posterImage)

        Glide.with(backdropImage)
                .load(movie.backdropPath?.let { MovieImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                .into(backdropImage)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
