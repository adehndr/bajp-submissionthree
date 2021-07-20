package com.example.submissionthree.di

import android.content.Context
import com.example.submissionthree.data.database.MovieRoomDatabase
import com.example.submissionthree.data.source.DataRepository
import com.example.submissionthree.data.source.LocalDataSource
import com.example.submissionthree.data.source.response.RemoteDataSource
import com.example.submissionthree.utils.AppExecutors
import com.example.submissionthree.utils.CallApiHelper

object Injection {

    fun provideRepository(context: Context): DataRepository {
        val database = MovieRoomDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(CallApiHelper())
        val appExecutors = AppExecutors()
        val localDataSource = LocalDataSource.getInstance(database.movieDao())

        return DataRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}