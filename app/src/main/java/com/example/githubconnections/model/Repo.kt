package com.example.githubconnections.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["id"])
data class Repo(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("description")
    val description: String?,
    @field:SerializedName("watchers_count")
    val watchersCount: Int,
    @field:SerializedName("forks_count")
    val forksCount: Int
)