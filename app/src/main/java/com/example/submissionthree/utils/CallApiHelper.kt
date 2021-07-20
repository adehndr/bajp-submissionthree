package com.example.submissionthree.utils

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.submissionthree.data.MovieResponse
import com.example.submissionthree.data.MoviesResponse
import com.example.submissionthree.data.TVSeriesResponse
import com.example.submissionthree.data.TvSerialResponse
import com.example.submissionthree.data.source.remote.ApiResponse
import com.example.submissionthree.service.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CallApiHelper {


    fun loadMovies(): MutableLiveData<ApiResponse<List<MovieResponse>>> {
        val serviceMovie = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        val postService = ApiConfig.getApiService().getMovie()
        postService.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(
                call: Call<MoviesResponse>,
                response: Response<MoviesResponse>
            ) {
                serviceMovie.value = response.body()?.let {
                    ApiResponse.success(it.movie)
                }
                if (!EspressoIdlingResource.idlingResource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                if (!EspressoIdlingResource.idlingResource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
                Log.d("Error", t.message.toString())
            }
        }
        )
        return serviceMovie
    }

    fun loadTVSeries(): MutableLiveData<ApiResponse<List<TvSerialResponse>>> {

        val serviceTVSeries = MutableLiveData<ApiResponse<List<TvSerialResponse>>>()
        val postService = ApiConfig.getApiServiceTV().getTvSerial()

        postService.enqueue(object : Callback<TVSeriesResponse> {
            override fun onResponse(
                call: Call<TVSeriesResponse>,
                response: Response<TVSeriesResponse>
            ) {
                serviceTVSeries.value = response.body()?.let {
                    ApiResponse.success(it.movie)
                }
                if (!EspressoIdlingResource.idlingResource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }

            override fun onFailure(call: Call<TVSeriesResponse>, t: Throwable) {
                Log.d("Error", t.message.toString())
                if (!EspressoIdlingResource.idlingResource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }

        })

        return serviceTVSeries

    }
}