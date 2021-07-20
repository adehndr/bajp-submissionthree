package com.example.submissionthree.service


import com.example.submissionthree.BuildConfig
import com.example.submissionthree.data.Entity.EntityMovie
import com.example.submissionthree.data.Entity.EntityTvSerial
import com.example.submissionthree.data.MoviesResponse
import com.example.submissionthree.data.TVSeriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("popular?api_key=${BuildConfig.API_KEY}&page=1")
    fun getMovie(
    ): Call<MoviesResponse>

    @GET("{id}?api_key=${BuildConfig.API_KEY}")
    fun getDetailMovie(
        @Path("id") id: String
    ): Call<EntityMovie>

    @GET("popular?api_key=${BuildConfig.API_KEY}&page=1")
    fun getTvSerial(
    ): Call<TVSeriesResponse>

    @GET("{id}?api_key=${BuildConfig.API_KEY}")
    fun getDetailTVSerial(
        @Path("id") id: String
    ): Call<EntityTvSerial>


}