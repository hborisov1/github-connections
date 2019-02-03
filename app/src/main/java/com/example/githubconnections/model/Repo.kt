package com.example.githubconnections.model

import androidx.room.Embedded
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
    val forksCount: Int,
    @field:SerializedName("owner")
    @field:Embedded(prefix = "owner_")
    val owner: Owner
) {
    data class Owner(
        @field:SerializedName("login")
        val username: String,
        @field:SerializedName("url")
        val url: String?
    )
}
