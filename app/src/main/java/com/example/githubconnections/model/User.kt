package com.example.githubconnections.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["username"])
data class User(
    @field:SerializedName("login")
    val username : String,
    @field:SerializedName("avatar_url")
    val avatarUrl: String?,
    @field:SerializedName("name")
    val name: String?,
    @field:SerializedName("bio")
    val bio: String?,
    @field:SerializedName("location")
    val location: String?,
    @field:SerializedName("followers")
    val followersCount: Int,
    @field:SerializedName("following")
    val followingCount: Int,
    @field:SerializedName("public_repos")
    val reposCount: Int
)