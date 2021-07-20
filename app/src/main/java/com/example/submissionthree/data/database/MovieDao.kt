package com.example.submissionthree.data.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.example.submissionthree.data.Entity.EntityMovie
import com.example.submissionthree.data.Entity.EntityTvSerial


@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPopularMovies(popularMovies: List<EntityMovie>)

    @Query("SELECT * FROM movie_entities")
    fun getAllMovies(): DataSource.Factory<Int, EntityMovie>

    @Query("SELECT * FROM movie_entities WHERE id = :movieId")
    fun getDetailMovie(movieId: String): LiveData<EntityMovie>

    @Query("SELECT * FROM movie_entities WHERE bookmarked = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, EntityMovie>

    @Update
    fun updateMovie(movieEntity: EntityMovie)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPopularTvSeries(popularTvSeries: List<EntityTvSerial>)

    @Query("SELECT * FROM tv_serial_entities")
    fun getAllTvSeries(): DataSource.Factory<Int, EntityTvSerial>

    @Query("SELECT * FROM tv_serial_entities WHERE id = :tvSerialId")
    fun getDetailTvSerial(tvSerialId: String): LiveData<EntityTvSerial>

    @Query("SELECT * FROM tv_serial_entities WHERE bookmarked = 1")
    fun getFavoriteTvSeries(): DataSource.Factory<Int, EntityTvSerial>

    @Update
    fun updateTvSerial(tvSerialEntity: EntityTvSerial)

}