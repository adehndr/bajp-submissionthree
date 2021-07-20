package com.example.submissionthree.ui.movieDatas

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.submissionthree.data.Entity.EntityMovie
import com.example.submissionthree.data.source.DataRepository
import com.example.submissionthree.ui.movies.MoviesViewModel
import com.example.submissionthree.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {
    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<EntityMovie>>>

    @Mock
    private lateinit var pagedList: PagedList<EntityMovie>

    @Before
    fun init() {
        viewModel = MoviesViewModel(movieRepository)
    }

    @Test
    fun getMovies() {
        val dummyMovies = Resource.success(pagedList)
        `when`(dummyMovies.data?.size).thenReturn(20)
        val movieData = MutableLiveData<Resource<PagedList<EntityMovie>>>()
        movieData.value = dummyMovies

        `when`(movieRepository.getMovie()).thenReturn(movieData)
        val movieDatas = viewModel.getMovies().value?.data
        verify(movieRepository).getMovie()
        assertNotNull(movieDatas)
        assertEquals(20, movieDatas?.size)

        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}