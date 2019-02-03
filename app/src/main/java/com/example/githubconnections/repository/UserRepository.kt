package com.example.githubconnections.repository

import androidx.lifecycle.LiveData
import com.example.githubconnections.AppExecutors
import com.example.githubconnections.api.ApiResponse
import com.example.githubconnections.api.GitHubService
import com.example.githubconnections.db.UserDao
import com.example.githubconnections.model.Resource
import com.example.githubconnections.model.User
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

            override fun shouldFetch(data: User?) = true

            override fun loadFromDb() = userDao.findByUsername(username)

            override fun createCall() = githubService.getUser(username)
        }.asLiveData()
    }

    fun loadFollowers(username: String, userType: String): LiveData<ApiResponse<List<User>>> {
        return githubService.getFollowersFollowing(
            username,
            userType
        ) // TODO do it with NetworkBoundResource - offline mode should be supported...
    }
}
