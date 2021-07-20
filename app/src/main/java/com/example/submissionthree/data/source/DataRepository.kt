package com.example.submissionthree.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.submissionthree.data.Entity.EntityMovie
import com.example.submissionthree.data.Entity.EntityTvSerial
import com.example.submissionthree.data.MovieResponse
import com.example.submissionthree.data.TvSerialResponse
import com.example.submissionthree.data.source.remote.ApiResponse
import com.example.submissionthree.data.source.response.RemoteDataSource
import com.example.submissionthree.utils.AppExecutors
import com.example.submissionthree.vo.Resource

class DataRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) :
    MovieDataSource {
    companion object {
        @Volatile
        private var instance: DataRepository? = null
        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): DataRepository =
            instance ?: synchronized(this) {
                instance ?: DataRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }

    override fun getMovie(): LiveData<Resource<PagedList<EntityMovie>>> {
        return object :
            NetworkBoundResource<PagedList<EntityMovie>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<EntityMovie>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllPopularMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<EntityMovie>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovie()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<EntityMovie>()
                for (response in data) {
                    val movie = EntityMovie(
                        response.title,
                        response.releaseDate,
                        response.score,
                        response.description,
                        response.imagePath,
                        false,
                        response.id
                    )
                    movieList.add(movie)
                }
                localDataSource.insertPopularMovies(movieList)
            }

        }.asLiveData()
    }

    override fun getDetailMovie(id: String): LiveData<EntityMovie> {
        return localDataSource.getMovieDetail(id)
    }

    override fun getTVSerial(): LiveData<Resource<PagedList<EntityTvSerial>>> {
        return object :
            NetworkBoundResource<PagedList<EntityTvSerial>, List<TvSerialResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<EntityTvSerial>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllPopularTvSeries(), config).build()
            }

            override fun shouldFetch(data: PagedList<EntityTvSerial>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvSerialResponse>>> =
                remoteDataSource.getTVSerial()

            override fun saveCallResult(data: List<TvSerialResponse>) {
                val tvSerialList = ArrayList<EntityTvSerial>()
                for (response in data) {
                    val tvSerial = EntityTvSerial(
                        response.title,
                        response.releaseDate,
                        response.score,
                        response.description,
                        response.imagePath,
                        false,
                        response.id
                    )
                    tvSerialList.add(tvSerial)
                }
                localDataSource.insertPopularTvSeries(tvSerialList)
            }

        }.asLiveData()
    }

    override fun getDetailTVSerial(id: String): LiveData<EntityTvSerial> {
        return localDataSource.getTvSerialDetail(id)
    }

    override fun setMovieFavorite(movieFavorited: EntityMovie, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setMovieBookmark(movieFavorited, state) }

    override fun getFavoritedMovies(): LiveData<PagedList<EntityMovie>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoritedMovies(), config).build()
    }

    override fun setTvSerialFavorite(tvSerialFavorited: EntityTvSerial, state: Boolean) =
        appExecutors.diskIO()
            .execute { localDataSource.setTvSerialBookmark(tvSerialFavorited, state) }

    override fun getFavoritedTvSerial(): LiveData<PagedList<EntityTvSerial>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoritedTvSeries(), config).build()
    }


}
