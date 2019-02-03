package com.example.githubconnections.repository

import androidx.lifecycle.LiveData
import com.example.githubconnections.AppExecutors
import com.example.githubconnections.api.GitHubService
import com.example.githubconnections.db.RepoDao
import com.example.githubconnections.model.Repo
import com.example.githubconnections.model.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepoRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val repoDao: RepoDao,
    private val githubService: GitHubService
) {

    fun loadRepos(owner: String): LiveData<Resource<List<Repo>>> {
        return object : NetworkBoundResource<List<Repo>, List<Repo>>(appExecutors) {
            override fun saveCallResult(item: List<Repo>) {
                repoDao.insertRepos(item)
            }

            override fun shouldFetch(data: List<Repo>?): Boolean {
                return data == null || data.isEmpty()// || repoListRateLimit.shouldFetch(owner)
            }

            override fun loadFromDb() = repoDao.loadRepositories(owner)

            override fun createCall() = githubService.getRepos(owner)

//            override fun onFetchFailed() {
//                repoListRateLimit.reset(owner)
//            }
        }.asLiveData()

    }
}
