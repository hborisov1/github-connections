package com.example.githubconnections.di

import android.app.Application
import androidx.room.Room
import com.example.githubconnections.api.GitHubService
import com.example.githubconnections.db.GitHubDb
import com.example.githubconnections.db.RepoDao
import com.example.githubconnections.db.UserDao
import com.example.githubconnections.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideGitHubService(): GitHubService {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(GitHubService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): GitHubDb {
        return Room
            .databaseBuilder(app, GitHubDb::class.java, "github.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: GitHubDb): UserDao {
        return db.userDao()
    }

    @Singleton
    @Provides
    fun provideRepoDao(db: GitHubDb): RepoDao {
        return db.repoDao()
    }
}
