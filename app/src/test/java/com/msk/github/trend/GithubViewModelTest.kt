package com.msk.github.trend

import com.msk.github.trend.db.GithubTrendDao
import com.msk.github.trend.model.GitHubRepositoryEntity
import com.msk.github.trend.service.Service
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.io.File

class GithubViewModelTest {

    private lateinit var gitHubRepositoryResponse : List<GitHubRepositoryEntity>

    @Mock
    private lateinit var service: Service

    @Mock
    private lateinit var daoGithub: GithubTrendDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val response = getJson("json/github_repo.json")
        val turnsType = object : TypeToken<List<GitHubRepositoryEntity>>(){}.type
        gitHubRepositoryResponse = Gson().fromJson<List<GitHubRepositoryEntity>>(response, turnsType)
        Mockito.`when`(service.getGithubRepositoryList()).thenReturn(Single.just(gitHubRepositoryResponse))
    }

    private fun getJson(path : String) : String {
        val file = File("src/test/resources/$path")
        return String(file.readBytes())
    }

    @Test
    fun `Test getGithubRepositoryList API should not null`() {
        assertNotNull(service.getGithubRepositoryList())
    }

    @Test
    fun `Test getGithubRepositoryList list data is not null`() {
        assertNotNull(service.getGithubRepositoryList()
            .test()
            .assertComplete()
            .assertValue {
                it.forEach {
                    it.author !=null
                    it.name !=null
                    it.forks !=null
                    it.languageColor !=null
                    it.language !=null
                    it.avatar !=null
                    it.time!=null
                    it.id !=null
                }!= null
            })
    }

    @Test
    fun `Test getGithubRepositoryList list data is not empty`() {
        assertNotNull(service.getGithubRepositoryList()
            .test()
            .assertComplete()
            .assertValue {
                it!=null && it.isNotEmpty()
            })
    }
}