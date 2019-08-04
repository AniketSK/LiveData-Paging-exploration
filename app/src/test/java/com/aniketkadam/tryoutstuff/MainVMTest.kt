package com.aniketkadam.tryoutstuff

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.aniketkadam.tryoutstuff.data.ImageListResult
import com.aniketkadam.tryoutstuff.data.LiveDataTestUtil
import com.aniketkadam.tryoutstuff.data.Repository
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as When

class MainVMTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var repo: Repository
    @Mock
    lateinit var imageListResult: ImageListResult
    lateinit var mainVM: MainVM

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        When(imageListResult.imageData).thenReturn(MutableLiveData())
        When(imageListResult.networkState).thenReturn(MutableLiveData())
        When(repo.getPagedImageResult()).thenReturn(imageListResult)
        mainVM = MainVM(repo)
    }

    @Test
    fun `when an item is selected, the selected item fragment is launched`() {
        assertThat(
            LiveDataTestUtil.getValue(mainVM.itemToNavigate),
            equalTo<PositionOnFragment?>(null) // We must return null to avoid trying to reset the values.
        )
        mainVM.setItemToNavigate(PositionOnFragment(ActiveFragment.Selection, 2))
        assertThat(
            LiveDataTestUtil.getValue(mainVM.itemToNavigate),
            equalTo<PositionOnFragment?>(PositionOnFragment(ActiveFragment.Selection, 2))
        )
    }
}