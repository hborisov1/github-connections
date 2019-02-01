package com.example.githubconnections.api

import com.example.githubconnections.model.Repo
import com.example.githubconnections.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface GitHubService {
    @GET("users/{username}")
    fun getUser(@Path ("username") username: String) : Call<User>

    @GET("users/{username}/repos")
    fun getRepos(@Path ("username") username: String) : Call<List<Repo>>

    @GET("users/{username}/followers")
    fun getFollowers(@Path ("username") username: String) : Call<List<User>>

    @GET("users/{username}/following")
    fun getFollowing(@Path ("username") username: String) : Call<List<User>>

}