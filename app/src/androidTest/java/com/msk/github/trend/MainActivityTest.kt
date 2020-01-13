package com.msk.github.trend

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.msk.github.trend.R
import org.junit.*

import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    var mainActivityTestRule = ActivityTestRule(MainActivity::class.java)

    var recyclerView:RecyclerView? = null

    @Before
    fun setUp() {
        recyclerView = mainActivityTestRule.activity.findViewById<RecyclerView>(R.id.githubRepoRecyclerView)
    }

    @Test
    fun testIsMainActivityInView() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun testGithubRepoRecyclerViewVisibility() {
        onView(withId(R.id.githubRepoRecyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun testSwipeRefreshRecyclerViewVisibility() {
        onView(withId(R.id.swipeRefreshRecyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun testFirstRowItemExpandClickItem() {
        onView(withId(R.id.githubRepoRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
    }

    @Test
    fun testFirstRowItemCollapseClickItem() {
        onView(withId(R.id.githubRepoRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,click()))
    }

    @Test
    fun testSecondRowItemExpandClickItem() {
        onView(withId(R.id.githubRepoRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1,click()))
    }

    @Test
    fun testSecondRowItemCollapseClickItem() {
        onView(withId(R.id.githubRepoRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1,click()))
    }

    @Test
    fun testRecyclerViewScrollTestRowItems() {
        var ItemCount = recyclerView?.adapter?.itemCount!!-1
        onView(withId(R.id.githubRepoRecyclerView)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(ItemCount))
    }

    @Test
    fun testRecyclerViewItemCount() {
        var itemCount = recyclerView?.adapter?.itemCount
        Assert.assertTrue(itemCount!! > 0)
    }
}