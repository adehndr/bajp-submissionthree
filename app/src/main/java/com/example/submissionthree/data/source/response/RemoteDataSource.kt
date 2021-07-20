package com.example.submissionthree.data.source.response

import androidx.lifecycle.LiveData
import com.example.submissionthree.data.MovieResponse
import com.example.submissionthree.data.TvSerialResponse
import com.example.submissionthree.data.source.remote.ApiResponse
import com.example.submissionthree.utils.CallApiHelper
import com.example.submissionthree.utils.EspressoIdlingResource

class RemoteDataSource(private val apiHelper: CallApiHelper) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: CallApiHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getMovie(): LiveData<ApiResponse<List<MovieResponse>>> {
        EspressoIdlingResource.increment()
        return apiHelper.loadMovies()
    }


    fun getTVSerial(): LiveData<ApiResponse<List<TvSerialResponse>>> {
        EspressoIdlingResource.increment()
        return apiHelper.loadTVSeries()
    }


}