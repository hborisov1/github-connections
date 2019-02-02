package com.example.githubconnections.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubconnections.model.Repo
import com.example.githubconnections.model.User

/**
 * Main database description.
 */
@Database(
    entities = [
        User::class,
        Repo::class],
    version = 1,
    exportSchema = false
)
abstract class GitHubDb : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun repoDao(): RepoDao
}
