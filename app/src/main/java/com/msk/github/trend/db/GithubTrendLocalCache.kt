package com.msk.github.trend.db

import androidx.lifecycle.LiveData
import com.msk.github.trend.model.GitHubRepositoryEntity
import java.util.concurrent.Executor

/**
 * Class that handles the DAO local data source. This ensures that methods are triggered on the
 * correct executor.
 */
class GithubTrendLocalCache(
    private val githubTrendDao: GithubTrendDao,
    private val ioExecutor: Executor) {

    /**
     * Insert a single record in the database, on a background thread.
     */
    fun insertGithubRepositoryList(repos: List<GitHubRepositoryEntity>) {
        ioExecutor.execute {
            githubTrendDao.saveGithubTrendRecord(repos)
        }
    }

    fun getGithubRepositoryRecordFromDB(): LiveData<List<GitHubRepositoryEntity>> {
        return githubTrendDao.loadGithubTrendRecords()
    }
}
