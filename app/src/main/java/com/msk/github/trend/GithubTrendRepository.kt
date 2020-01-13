package com.msk.github.trend

import androidx.lifecycle.LiveData
import com.msk.github.trend.db.GithubTrendLocalCache
import com.msk.github.trend.model.GitHubRepositoryEntity
import com.msk.github.trend.service.Service
import io.reactivex.Single
import javax.inject.Inject

class GithubTrendRepository @Inject constructor(private val service: Service,
                                                private val cache: GithubTrendLocalCache) {

    fun fetchRepositoryDetailsFromService(): Single<List<GitHubRepositoryEntity>>{
        return service.getGithubRepositoryList()
    }

    fun saveRepositoryDetailsRecord(githubRepoList: List<GitHubRepositoryEntity>) {
        cache.insertGithubRepositoryList(githubRepoList)
    }

    fun getRepositoryCacheLiveData(): LiveData<List<GitHubRepositoryEntity>> {
        return cache.getGithubRepositoryRecordFromDB()
    }
}
