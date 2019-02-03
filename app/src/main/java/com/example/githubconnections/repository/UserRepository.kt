package com.example.githubconnections.repository

import androidx.lifecycle.LiveData
import com.example.githubconnections.AppExecutors
import com.example.githubconnections.api.ApiResponse
import com.example.githubconnections.api.GitHubService
import com.example.githubconnections.db.UserDao
import com.example.githubconnections.model.Resource
import com.example.githubconnections.model.User
import com.example.githubconnections.utils.AbsentLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val userDao: UserDao,
    private val githubService: GitHubService
) {

    fun loadUser(username: String): LiveData<Resource<User>> {
        return object : NetworkBoundResource<User, User>(appExecutors) {
            override fun saveCallResult(item: User) {
                userDao.insert(item)
            }

            override fun shouldFetch(data: User?) = data == null

            override fun loadFromDb() = userDao.findByUsername(username)

            override fun createCall() = githubService.getUser(username)
        }.asLiveData()
    }

    fun loadFollowers(username: String): LiveData<Resource<List<User>>> {
        return object : NetworkBoundResource<List<User>, List<User>>(appExecutors) {
            override fun shouldFetch(data: List<User>?): Boolean = true //TODO always fetch for now

            override fun loadFromDb(): LiveData<List<User>> = AbsentLiveData.create() //TODO should change this..

            override fun createCall(): LiveData<ApiResponse<List<User>>> = githubService.getFollowers(username)

            override fun saveCallResult(item: List<User>) {
                userDao.insertUsers(item)
            }
        }.asLiveData()
    }
}
