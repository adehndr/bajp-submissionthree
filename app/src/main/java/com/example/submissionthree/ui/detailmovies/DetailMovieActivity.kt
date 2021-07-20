package com.example.submissionthree.ui.detailmovies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.submissionthree.R
import com.example.submissionthree.data.Entity.EntityMovie
import com.example.submissionthree.databinding.ActivityDetailBinding
import com.example.submissionthree.viewmodel.ViewModelFactory

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var movieEntity: EntityMovie
    private lateinit var detailMovieBinding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailMovieBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailMovieBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val movieSelected = intent.getStringExtra(EXTRA_MOVIE)
        val viewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(applicationContext)
        )[DetailMoviesViewModel::class.java]
        if (movieSelected != null) {
            viewModel.setSelectedMovie(movieSelected)
            viewModel.getSelectedMovie().observe(this,
                {
                    movieEntity = it
                    inflateActivity(movieEntity)
                    SetFloatingButtonFavorite(movieEntity.bookmarked)
                })

        }
        detailMovieBinding.fabFavorite.setOnClickListener {
            movieEntity.bookmarked = !movieEntity.bookmarked
            viewModel.setMovieBookmark(movieEntity, movieEntity.bookmarked)
        }

    }

    private fun inflateActivity(movieEntity: EntityMovie) {
        detailMovieBinding.tvMovieTitle.text = movieEntity.title
        detailMovieBinding.tvReleaseDate.text = movieEntity.releaseDate
        detailMovieBinding.tvMovieScore.text = movieEntity.score
        detailMovieBinding.tvMovieDesc.text = movieEntity.description
        Glide.with(this)
            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2" + movieEntity.imagePath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(detailMovieBinding.imagePoster)
    }

    private fun SetFloatingButtonFavorite(state: Boolean) {
        val floatButton = detailMovieBinding.fabFavorite
        if (state) {
            floatButton.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_24
                )
            )
        } else {
            floatButton.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_baseline_favorite_border_24
                )
            )

        }
    }
}