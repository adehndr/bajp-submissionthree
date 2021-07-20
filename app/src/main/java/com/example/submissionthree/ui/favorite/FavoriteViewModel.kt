package com.example.submissionthree.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.submissionthree.data.Entity.EntityMovie
import com.example.submissionthree.data.Entity.EntityTvSerial
import com.example.submissionthree.data.source.DataRepository

class FavoriteViewModel(private val movieRepository: DataRepository) : ViewModel() {
    fun getMovies(): LiveData<PagedList<EntityMovie>> = movieRepository.getFavoritedMovies()
    fun getTvSeries(): LiveData<PagedList<EntityTvSerial>> = movieRepository.getFavoritedTvSerial()
}