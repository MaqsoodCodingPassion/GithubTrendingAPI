package com.msk.github.trend

import android.content.Context
import android.view.View
import com.msk.github.trend.util.getTimeDiff
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GithubTrendUtilTest {

    @Mock
    private lateinit var context:Context

    @Mock
    private lateinit var view: View

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        view = View(context)
    }

    @Test
    fun `Test getTimeDiff API is greater than 2 hours`(){
        Assert.assertTrue(getTimeDiff(3571163875775, 1571163875775) > 2)
    }

    @Test
    fun `Test getTimeDiff API is not greater than 2 hours`(){
        Assert.assertFalse(getTimeDiff(1571163875775, 1571163875775) > 2)
    }

    @Test
    fun getSORT_BY_STARS() {
    }

    @Test
    fun getSORT_BY_NAMES() {
    }

    @Test
    fun collapse() {
    }

   /* @Test
    fun isNetworkConnectedTest() {
        Assert.assertTrue(isNetworkConnected(context))
    }*/

   /* @Test
    fun `Test showView API visble or not`(){
        verify(view.showView(true))
    }*/
}