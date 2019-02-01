package com.example.githubconnections.model

import com.google.gson.annotations.SerializedName


data class Repo(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("description")
    val description: String?,
    @field:SerializedName("watchers_count")
    val watchersCount: Int,
    @field:SerializedName("forks_count")
    val forksCount: Int
)