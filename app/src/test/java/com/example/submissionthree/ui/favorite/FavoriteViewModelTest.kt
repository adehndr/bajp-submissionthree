package com.example.submissionthree.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.submissionthree.data.Entity.EntityMovie
import com.example.submissionthree.data.Entity.EntityTvSerial
import com.example.submissionthree.data.source.DataRepository
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {
    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: DataRepository

    @Mock
    private lateinit var movieObserver: Observer<PagedList<EntityMovie>>

    @Mock
    private lateinit var pagedListMovie: PagedList<EntityMovie>

    @Mock
    private lateinit var tvSerialObserver: Observer<PagedList<EntityTvSerial>>

    @Mock
    private lateinit var pagedListTvSerial: PagedList<EntityTvSerial>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(movieRepository)
    }


    @Test
    fun getMovies() {

        val dummyMovie = pagedListMovie
        Mockito.`when`(dummyMovie.size).thenReturn(20)
        val movie = MutableLiveData<PagedList<EntityMovie>>()
        movie.value = dummyMovie

        Mockito.`when`(movieRepository.getFavoritedMovies()).thenReturn(movie)

        val movieEntities = viewModel.getMovies().value
        verify(movieRepository).getFavoritedMovies()
        assertNotNull(movieEntities)
        assertEquals(20, movieEntities?.size)

        viewModel.getMovies().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getTvSeries() {

        val dummyTvshow = pagedListTvSerial
        Mockito.`when`(dummyTvshow.size).thenReturn(20)
        val tvSerial = MutableLiveData<PagedList<EntityTvSerial>>()
        tvSerial.value = dummyTvshow

        Mockito.`when`(movieRepository.getFavoritedTvSerial()).thenReturn(tvSerial)
        val tvSerialEntities = viewModel.getTvSeries().value
        verify(movieRepository).getFavoritedTvSerial()
        assertNotNull(tvSerialEntities)
        assertEquals(20, tvSerialEntities?.size)

        viewModel.getTvSeries().observeForever(tvSerialObserver)
        verify(tvSerialObserver).onChanged(dummyTvshow)

    }
}