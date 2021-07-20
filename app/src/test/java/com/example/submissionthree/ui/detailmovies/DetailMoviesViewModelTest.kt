package com.example.submissionthree.ui.detailmovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.submissionthree.data.Entity.EntityMovie
import com.example.submissionthree.data.source.DataRepository
import com.example.submissionthree.utils.DataDummy
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
class DetailMoviesViewModelTest {

    private lateinit var viewModel: DetailMoviesViewModel
    private val dummyMovies = DataDummy.generateDummyMovies()[0]
    private val dummyMovieId = dummyMovies.id
    private val dummyDetailMovie = DataDummy.generateDummyDetailMovie(dummyMovieId)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: DataRepository

    @Mock
    private lateinit var movieObserver: Observer<EntityMovie>

    @Before
    fun setUp() {
        viewModel = DetailMoviesViewModel(movieRepository)
        viewModel.setSelectedMovie(dummyMovieId)

    }

    @Test
    fun getSelectedMovie() {
        val movie = MutableLiveData<EntityMovie>()
        movie.value = dummyDetailMovie

        Mockito.`when`(movieRepository.getDetailMovie(dummyMovieId)).thenReturn(movie)
        val movieEntity = viewModel.getSelectedMovie().value as EntityMovie
        Mockito.verify(movieRepository).getDetailMovie(dummyMovieId)
        assertNotNull(movieEntity)
        assertEquals(dummyDetailMovie.description, movieEntity.description)
        assertEquals(dummyDetailMovie.title, movieEntity.title)
        assertEquals(dummyDetailMovie.id, movieEntity.id)
        assertEquals(dummyDetailMovie.imagePath, movieEntity.imagePath)
        assertEquals(dummyDetailMovie.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyDetailMovie.score, movieEntity.score)
        assertEquals(dummyDetailMovie.bookmarked, movieEntity.bookmarked)


        viewModel.getSelectedMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyDetailMovie)
    }
}