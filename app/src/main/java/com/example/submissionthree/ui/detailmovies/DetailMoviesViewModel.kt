package com.example.submissionthree.ui.detailmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submissionthree.data.Entity.EntityMovie
import com.example.submissionthree.data.source.DataRepository

class DetailMoviesViewModel(private val movieRepository: DataRepository) : ViewModel() {
    lateinit var movieId: String
    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun getSelectedMovie(): LiveData<EntityMovie> {
        return movieRepository.getDetailMovie(movieId)
    }

    fun setMovieBookmark(movieEntity: EntityMovie, state: Boolean) {
        movieRepository.setMovieFavorite(movieEntity, state)
    }

}