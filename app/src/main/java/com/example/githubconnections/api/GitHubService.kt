package com.example.githubconnections.api

import androidx.lifecycle.LiveData
import com.example.githubconnections.model.Repo
import com.example.githubconnections.model.User
import retrofit2.http.GET
import retrofit2.http.Path


interface GitHubService {
    @GET("users/{username}")
    fun getUser(@Path("username") username: String): LiveData<ApiResponse<User>>

    @GET("users/{username}/repos")
    fun getRepos(@Path("username") username: String): LiveData<ApiResponse<List<Repo>>>

    @GET("users/{username}/{usersType}")
    fun getFollowersFollowing(@Path("username") username: String, @Path("usersType") usersType: String): LiveData<ApiResponse<List<User>>>

}