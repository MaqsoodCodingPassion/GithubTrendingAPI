package com.msk.github.trend.di.module

import androidx.room.Room
import com.msk.github.trend.GithubTrendApplication
import com.msk.github.trend.db.GithubTrendDao
import com.msk.github.trend.db.GithubTrendDatabase
import com.msk.github.trend.db.GithubTrendLocalCache
import com.msk.github.trend.di.BaseUrl
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @BaseUrl
    fun provideBaseUrl(): String {
        return "https://github-trending-api.now.sh"
    }

    @Provides
    @Singleton
    fun provideGithubTrendDatabase(application: GithubTrendApplication): GithubTrendDatabase {
        return Room.databaseBuilder(application, GithubTrendDatabase::class.java, "github.db").build()
    }

    @Provides
    @Singleton
    fun provideGithubTrendDao(database: GithubTrendDatabase): GithubTrendDao {
        return database.githubTrendDao()
    }

    @Provides
    @Singleton
    fun provideGithubTrendLocalCache(database: GithubTrendDatabase): GithubTrendLocalCache {
        return GithubTrendLocalCache(database.githubTrendDao(), Executors.newSingleThreadExecutor())
    }
}