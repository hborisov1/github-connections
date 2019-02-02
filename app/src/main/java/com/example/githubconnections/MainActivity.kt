package com.example.githubconnections

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.githubconnections.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
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
