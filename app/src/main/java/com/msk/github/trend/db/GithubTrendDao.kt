package com.msk.github.trend.db


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.msk.github.trend.model.GitHubRepositoryEntity

@Dao
interface GithubTrendDao {

    @Query("SELECT * FROM github")
    fun loadGithubTrendRecords(): LiveData<List<GitHubRepositoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveGithubTrendRecord(GitHubRepositoryEntityDetails: List<GitHubRepositoryEntity>)
}
