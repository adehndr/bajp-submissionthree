package com.example.submissionthree.data.source

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.submissionthree.data.Entity.EntityMovie
import com.example.submissionthree.data.Entity.EntityTvSerial
import com.example.submissionthree.data.database.MovieDao

class LocalDataSource private constructor(private val mMovieDao: MovieDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieDao)
    }

    fun insertPopularMovies(popularMovies: List<EntityMovie>) =
        mMovieDao.insertPopularMovies(popularMovies)

    fun getAllPopularMovies(): DataSource.Factory<Int, EntityMovie> = mMovieDao.getAllMovies()
    fun getMovieDetail(movieId: String): LiveData<EntityMovie> = mMovieDao.getDetailMovie(movieId)
    fun getFavoritedMovies(): DataSource.Factory<Int, EntityMovie> = mMovieDao.getFavoriteMovies()
    fun setMovieBookmark(movie: EntityMovie, newState: Boolean) {
        movie.bookmarked = newState
        mMovieDao.updateMovie(movie)
    }

    fun insertPopularTvSeries(popularTvSeries: List<EntityTvSerial>) =
        mMovieDao.insertPopularTvSeries(popularTvSeries)

    fun getAllPopularTvSeries(): DataSource.Factory<Int, EntityTvSerial> =
        mMovieDao.getAllTvSeries()

    fun getTvSerialDetail(tvSerialId: String): LiveData<EntityTvSerial> =
        mMovieDao.getDetailTvSerial(tvSerialId)

    fun getFavoritedTvSeries(): DataSource.Factory<Int, EntityTvSerial> =
        mMovieDao.getFavoriteTvSeries()

    fun setTvSerialBookmark(tvSerial: EntityTvSerial, newState: Boolean) {
        tvSerial.bookmarked = newState
        mMovieDao.updateTvSerial(tvSerial)
    }
}