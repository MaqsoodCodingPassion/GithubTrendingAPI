package com.msk.github.trend.model

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GitHubRepositoryEntityTest {

    private var repositoryEntity = GitHubRepositoryEntity()

    @Before
    fun setUp() {
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
    }

    @Test
    fun `Test getId method is not null`(){
        assertNotNull(repositoryEntity.id)
    }

    @Test
    fun `Test getName method is not null`(){
        assertNotNull(repositoryEntity.name)
    }

    @Test
    fun `Test getForks method is not null`(){
        assertNotNull(repositoryEntity.forks)
        assertNotNull(repositoryEntity.forks > 0)
    }

    @Test
    fun `Test getAuthor method is not null`(){
        assertNotNull(repositoryEntity.author)
    }

    @Test
    fun `Test getDescription method is not null`(){
        assertNotNull(repositoryEntity.description)
    }

    @Test
    fun `Test getLanguage method is not null`(){
        assertNotNull(repositoryEntity.language)
    }

    @Test
    fun `Test getAvatar method is not null`(){
        assertNotNull(repositoryEntity.avatar)
    }

    @Test
    fun `Test getLanguageColor method is not null`(){
        assertNotNull(repositoryEntity.languageColor)
    }

    @Test
    fun `Test getStars method is not null`(){
        assertNotNull(repositoryEntity.stars)
    }

    @Test
    fun `Test getUrl method is not null`(){
        assertNotNull(repositoryEntity.url)
    }


    @Test
    fun `Test getCurrentPeriodStars method is not null`(){
        assertNotNull(repositoryEntity.currentPeriodStars)
    }

    @Test
    fun `Test getTime method is not null`(){
        assertNotNull(repositoryEntity.time)
    }
}