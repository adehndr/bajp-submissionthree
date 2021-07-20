package com.example.submissionthree.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submissionthree.data.source.DataRepository
import com.example.submissionthree.di.Injection
import com.example.submissionthree.ui.detailmovies.DetailMoviesViewModel
import com.example.submissionthree.ui.detailtvseries.DetailTvSeriesViewModel
import com.example.submissionthree.ui.favorite.FavoriteViewModel
import com.example.submissionthree.ui.movies.MoviesViewModel
import com.example.submissionthree.ui.tvseries.TVSeriesViewModel

class ViewModelFactory private constructor(private val mMovieRepository: DataRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesViewModel::class.java) -> {
                MoviesViewModel(mMovieRepository) as T
            }

            modelClass.isAssignableFrom(TVSeriesViewModel::class.java) -> {
                TVSeriesViewModel(mMovieRepository) as T
            }
            modelClass.isAssignableFrom(DetailMoviesViewModel::class.java) -> {
                DetailMoviesViewModel(mMovieRepository) as T
            }

            modelClass.isAssignableFrom(DetailTvSeriesViewModel::class.java) -> {
                DetailTvSeriesViewModel(mMovieRepository) as T
            }

            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(mMovieRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
