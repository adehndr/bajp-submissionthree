package com.example.submissionthree.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.submissionthree.data.Entity.EntityMovie
import com.example.submissionthree.data.Entity.EntityTvSerial
import com.example.submissionthree.data.source.response.RemoteDataSource
import com.example.submissionthree.util.LiveDataTestUtil
import com.example.submissionthree.util.PagedListUtil
import com.example.submissionthree.utils.AppExecutors
import com.example.submissionthree.utils.DataDummy
import com.example.submissionthree.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class DataRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)

    private val movieRepository = FakeDataRepository(remote, local, appExecutors)
    private val movieResponses = DataDummy.generateDummyMovies()
    private val movieId = movieResponses[0].id
    private val detailMovieResponse = DataDummy.generateDummyDetailMovie(movieId)

    private val tvSeriesRepository = FakeDataRepository(remote, local, appExecutors)
    private val tvSerialResponse = DataDummy.generateDummySeries()
    private val tvSerialId = tvSerialResponse[0].id
    private val detailTVSerialResponse = DataDummy.generateDummyDetailTVSerial(tvSerialId)

    @Test
    fun getMovie() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, EntityMovie>
        Mockito.`when`(local.getAllPopularMovies()).thenReturn(dataSourceFactory)
        movieRepository.getMovie()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getAllPopularMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailMovie() {
        val dummyMovie = MutableLiveData<EntityMovie>()
        dummyMovie.value = DataDummy.generateDummyDetailMovie(movieId)
        Mockito.`when`(local.getMovieDetail(movieId)).thenReturn(dummyMovie)

        val movieEntity = LiveDataTestUtil.getValue(movieRepository.getDetailMovie(movieId))
        verify(local).getMovieDetail(movieId)
        assertNotNull(movieEntity)
        assertEquals(detailMovieResponse.title, movieEntity.title)
        assertEquals(detailMovieResponse.description, movieEntity.description)
        assertEquals(detailMovieResponse.score, movieEntity.score)
        assertEquals(detailMovieResponse.releaseDate, movieEntity.releaseDate)
        assertEquals(detailMovieResponse.imagePath, movieEntity.imagePath)
        assertEquals(detailMovieResponse.id, movieEntity.id)
        assertEquals(detailMovieResponse.bookmarked, movieEntity.bookmarked)
    }


    @Test
    fun getFavoritedMovies() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, EntityMovie>
        Mockito.`when`(local.getFavoritedMovies()).thenReturn(dataSourceFactory)
        movieRepository.getFavoritedMovies()

        val movieEntities =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getFavoritedMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())

    }

    @Test
    fun getTVSerial() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, EntityTvSerial>
        Mockito.`when`(local.getAllPopularTvSeries()).thenReturn(dataSourceFactory)
        tvSeriesRepository.getTVSerial()

        val tvSeriesEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummySeries()))
        verify(local).getAllPopularTvSeries()
        assertNotNull(tvSeriesEntity.data)
        assertEquals(tvSerialResponse.size.toLong(), tvSeriesEntity.data?.size?.toLong())

    }

    @Test
    fun getDetailTVSerial() {

        val dummyTvshow = MutableLiveData<EntityTvSerial>()
        dummyTvshow.value = DataDummy.generateDummyDetailTVSerial(tvSerialId)
        Mockito.`when`(local.getTvSerialDetail(tvSerialId)).thenReturn(dummyTvshow)

        val tvSerialEntity =
            LiveDataTestUtil.getValue(tvSeriesRepository.getDetailTVSerial(tvSerialId))
        verify(local).getTvSerialDetail(tvSerialId)
        assertNotNull(tvSerialEntity)
        assertEquals(detailTVSerialResponse.title, tvSerialEntity.title)
        assertEquals(detailTVSerialResponse.description, tvSerialEntity.description)
        assertEquals(detailTVSerialResponse.score, tvSerialEntity.score)
        assertEquals(detailTVSerialResponse.releaseDate, tvSerialEntity.releaseDate)
        assertEquals(detailTVSerialResponse.imagePath, tvSerialEntity.imagePath)
        assertEquals(detailTVSerialResponse.id, tvSerialEntity.id)
        assertEquals(detailTVSerialResponse.bookmarked, tvSerialEntity.bookmarked)

    }

    @Test
    fun getFavoritedTvSerial() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, EntityTvSerial>
        Mockito.`when`(local.getFavoritedTvSeries()).thenReturn(dataSourceFactory)
        tvSeriesRepository.getFavoritedTvSerial()

        val tvSeriesEntity =
            Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummySeries()))
        verify(local).getFavoritedTvSeries()
        assertNotNull(tvSeriesEntity)
        assertEquals(tvSerialResponse.size.toLong(), tvSeriesEntity.data?.size?.toLong())
    }
}