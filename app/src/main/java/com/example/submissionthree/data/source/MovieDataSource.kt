package com.example.submissionthree.data.source


import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.submissionthree.data.Entity.EntityMovie
import com.example.submissionthree.data.Entity.EntityTvSerial
import com.example.submissionthree.vo.Resource

interface MovieDataSource {
    fun getMovie(): LiveData<Resource<PagedList<EntityMovie>>>

    fun getDetailMovie(id: String): LiveData<EntityMovie>

    fun getTVSerial(): LiveData<Resource<PagedList<EntityTvSerial>>>

    fun getDetailTVSerial(id: String): LiveData<EntityTvSerial>

    fun setMovieFavorite(movieFavorited: EntityMovie, state: Boolean)

    fun getFavoritedMovies(): LiveData<PagedList<EntityMovie>>

    fun setTvSerialFavorite(tvSerialFavorited: EntityTvSerial, state: Boolean)

    fun getFavoritedTvSerial(): LiveData<PagedList<EntityTvSerial>>
}