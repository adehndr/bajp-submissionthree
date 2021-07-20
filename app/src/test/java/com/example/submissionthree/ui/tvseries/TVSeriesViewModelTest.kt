package com.example.submissionthree.ui.tvseries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.submissionthree.data.Entity.EntityTvSerial
import com.example.submissionthree.data.source.DataRepository
import com.example.submissionthree.vo.Resource
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
class TVSeriesViewModelTest {


    private lateinit var viewModel: TVSeriesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvSerialRepository: DataRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<EntityTvSerial>>>

    @Mock
    private lateinit var pagedList: PagedList<EntityTvSerial>

    @Before
    fun init() {
        viewModel = TVSeriesViewModel(tvSerialRepository)
    }

    @Test
    fun getSeries() {

        val dummyTvSeries = Resource.success(pagedList)
        Mockito.`when`(dummyTvSeries.data?.size).thenReturn(20)
        val tvSerial = MutableLiveData<Resource<PagedList<EntityTvSerial>>>()
        tvSerial.value = dummyTvSeries

        Mockito.`when`(tvSerialRepository.getTVSerial()).thenReturn(tvSerial)
        val tvSerialEntities = viewModel.getSeries().value?.data
        Mockito.verify(tvSerialRepository).getTVSerial()
        assertNotNull(tvSerialEntities)
        assertEquals(20, tvSerialEntities?.size)

        viewModel.getSeries().observeForever(observer)
        Mockito.verify(observer).onChanged(dummyTvSeries)
    }
}