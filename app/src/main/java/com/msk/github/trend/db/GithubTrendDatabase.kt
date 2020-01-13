package com.msk.github.trend.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.msk.github.trend.model.GitHubRepositoryEntity

@Database(entities = [GitHubRepositoryEntity::class], version = 1,exportSchema = false)
abstract class GithubTrendDatabase : RoomDatabase() {
    abstract fun githubTrendDao(): GithubTrendDao
}