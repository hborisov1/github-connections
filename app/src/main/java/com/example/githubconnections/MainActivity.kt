package com.example.githubconnections

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.githubconnections.utils.SharedPrefsUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!SharedPrefsUtils(this).isLoggedIn()) {
            findNavController(fragmentContainer.id).popBackStack()
            findNavController(fragmentContainer.id).navigate(R.id.loginFragment)
        }
    }

}
