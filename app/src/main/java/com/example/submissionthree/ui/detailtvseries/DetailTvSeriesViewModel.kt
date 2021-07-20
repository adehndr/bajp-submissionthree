package com.example.submissionthree.ui.detailtvseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.submissionthree.data.Entity.EntityTvSerial
import com.example.submissionthree.data.source.DataRepository

class DetailTvSeriesViewModel(private val movieRepository: DataRepository) : ViewModel() {

    lateinit var movieId: String
    fun setSelectedTVSerial(movieId: String) {
        this.movieId = movieId
    }

    fun getSelectedTVSerial(): LiveData<EntityTvSerial> {
        return movieRepository.getDetailTVSerial(movieId)
    }

    fun setTvSerialBookmark(tvSerial: EntityTvSerial, state: Boolean) {
        movieRepository.setTvSerialFavorite(tvSerial, state)
    }


}