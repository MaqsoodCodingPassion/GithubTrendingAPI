package com.msk.github.trend.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.msk.github.trend.GithubTrendRepository
import com.msk.github.trend.GithubTrendViewModel
import javax.inject.Inject

class ViewModelFactory
    @Inject constructor(private val repository: GithubTrendRepository)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(GithubTrendViewModel::class.java) -> GithubTrendViewModel(
                repository
            ) as T
            else -> throw IllegalArgumentException("Unknown ViewModel Class ${modelClass.name}")
        }
    }
}