package com.msk.github.trend.service

import com.msk.github.trend.model.GitHubRepositoryEntity
import io.reactivex.Single
import retrofit2.http.GET

interface Service {

    //Github repository API
    @GET("/repositories")
    fun getGithubRepositoryList(): Single<List<GitHubRepositoryEntity>>
}
