package com.msk.github.trend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msk.github.trend.model.GitHubRepositoryEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class GithubTrendViewModel(private val repository: GithubTrendRepository) : ViewModel() {

    fun saveDataToDB(repoEntities: List<GitHubRepositoryEntity>) {
        repository.saveRepositoryDetailsRecord(repoEntities)
    }

    fun fetchGithubRepoRecordsFromDB(): LiveData<List<GitHubRepositoryEntity>> {
        return repository.getRepositoryCacheLiveData()
    }

    fun fetchGithubRepoDataFromAPI(): MutableLiveData<List<GitHubRepositoryEntity>?> {

        var repoListResponse: MutableLiveData<List<GitHubRepositoryEntity>?> = MutableLiveData()

        val observable = repository.fetchRepositoryDetailsFromService()
        var dbRowID = 0
        observable.map<List<GitHubRepositoryEntity>> {
            it.forEach {
                it.id += dbRowID
                dbRowID += 1
                it.time = Date().time
            }
            repository.saveRepositoryDetailsRecord(it)
            it
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    repoListResponse.value = it
                },
                {
                    repoListResponse.value = null
                })

        return repoListResponse
    }
}