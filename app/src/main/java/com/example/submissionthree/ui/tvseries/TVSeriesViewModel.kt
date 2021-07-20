package com.example.submissionthree.ui.tvseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.submissionthree.data.Entity.EntityTvSerial
import com.example.submissionthree.data.source.DataRepository
import com.example.submissionthree.vo.Resource

class TVSeriesViewModel(val movieRepository: DataRepository) : ViewModel() {
    fun getSeries(): LiveData<Resource<PagedList<EntityTvSerial>>> = movieRepository.getTVSerial()
}