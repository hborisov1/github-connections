package com.example.githubconnections

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.githubconnections.api.GitHubService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
// TODO remove this code which was used for testing purposes
//        val service = Retrofit.Builder()
//            .baseUrl("https://api.github.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(GitHubService::class.java)
//
//        Thread(Runnable {
//            val user = service.getFollowing("hborisov1").execute()
//            Log.i("ICO", user.body().toString())
//        }).start()

    }
}
