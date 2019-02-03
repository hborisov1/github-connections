package com.example.githubconnections.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubconnections.model.Repo
import com.example.githubconnections.model.User

/**
 * Interface for database access for Repo related operations.
 */
@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRepos(repos: List<Repo>)

    @Query(
        """
        SELECT * FROM Repo
        WHERE owner_username = :owner
        ORDER BY forksCount DESC"""
    )
    fun loadRepositories(owner: String): LiveData<List<Repo>>
}
