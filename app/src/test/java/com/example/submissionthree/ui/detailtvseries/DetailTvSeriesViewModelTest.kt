package com.example.submissionthree.ui.detailtvseries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.submissionthree.data.Entity.EntityTvSerial
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
class DetailTvSeriesViewModelTest {
    private lateinit var viewModel: DetailTvSeriesViewModel
    private val dummyTVSerial = DataDummy.generateDummySeries()[0]
    private val tvSerialId = dummyTVSerial.id
    private val dummyDetailTVSerial = DataDummy.generateDummyDetailTVSerial(tvSerialId)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvSerialRepository: DataRepository

    @Mock
    private lateinit var tvSerialObserver: Observer<EntityTvSerial>

    @Before
    fun setUp() {
        viewModel = DetailTvSeriesViewModel(tvSerialRepository)
        viewModel.setSelectedTVSerial(tvSerialId)

    }

    @Test
    fun getSelectedTVSerial() {
        val tvSerial = MutableLiveData<EntityTvSerial>()
        tvSerial.value = dummyDetailTVSerial

        Mockito.`when`(tvSerialRepository.getDetailTVSerial(tvSerialId)).thenReturn(tvSerial)
        val tvSerialEntity = viewModel.getSelectedTVSerial().value as EntityTvSerial
        Mockito.verify(tvSerialRepository).getDetailTVSerial(tvSerialId)
        assertNotNull(tvSerialEntity)
        assertEquals(dummyDetailTVSerial.description, tvSerialEntity.description)
        assertEquals(dummyDetailTVSerial.title, tvSerialEntity.title)
        assertEquals(dummyDetailTVSerial.id, tvSerialEntity.id)
        assertEquals(dummyDetailTVSerial.imagePath, tvSerialEntity.imagePath)
        assertEquals(dummyDetailTVSerial.releaseDate, tvSerialEntity.releaseDate)
        assertEquals(dummyDetailTVSerial.score, tvSerialEntity.score)
        assertEquals(dummyDetailTVSerial.bookmarked, tvSerialEntity.bookmarked)

        viewModel.getSelectedTVSerial().observeForever(tvSerialObserver)
        verify(tvSerialObserver).onChanged(dummyDetailTVSerial)
    }
}