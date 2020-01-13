package com.msk.github.trend.db

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.room.Room
import com.msk.github.trend.model.GitHubRepositoryEntity
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.io.IOException

class TestGithubEntityDB {

    private lateinit var daoGithub: GithubTrendDao
    private lateinit var db: GithubTrendDatabase

    private lateinit var handler : Handler

    @Mock
    private lateinit var context: Context

    private var entities: ArrayList<GitHubRepositoryEntity> = ArrayList()

    @Before
    fun createDb() {
        MockitoAnnotations.initMocks(this)

        //init DB
        db = Room.inMemoryDatabaseBuilder(
            context, GithubTrendDatabase::class.java).build()
        daoGithub = db.githubTrendDao()

        var repositoryEntity = GitHubRepositoryEntity()
        repositoryEntity.author = "mohammed"
        repositoryEntity.name = "khan"
        repositoryEntity.languageColor = "blue"
        repositoryEntity.avatar = "http://github.com/mohammed.png"
        repositoryEntity.currentPeriodStars = 10
        repositoryEntity.description = "Java developer"
        repositoryEntity.id = 1
        repositoryEntity.forks = 32
        repositoryEntity.language = "java"
        repositoryEntity.time = 34433
        repositoryEntity.url = "http://github.com"
        entities.add(repositoryEntity)

        handler = Handler(Looper.getMainLooper())
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun `Test weather DB is not null`(){
        assertNotNull(db.githubTrendDao())
    }

    @Test
    fun `Test loadGithubRepoData api is not null`(){
        handler.post(Runnable {
            assertNotNull(daoGithub.loadGithubTrendRecords())
        })
    }

    @Test
    fun `Test saveGithubRepoRecords api is not null`(){
        handler.post(Runnable {
            assertNotNull(daoGithub.saveGithubTrendRecord(entities))
        })
    }
}
