package com.example.submissionthree.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.submissionthree.data.Entity.EntityMovie
import com.example.submissionthree.data.source.DataRepository
import com.example.submissionthree.vo.Resource

class MoviesViewModel(val movieRepository: DataRepository) : ViewModel() {
    fun getMovies(): LiveData<Resource<PagedList<EntityMovie>>> = movieRepository.getMovie()
}