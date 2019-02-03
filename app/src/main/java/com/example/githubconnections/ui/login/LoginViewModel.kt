package com.example.githubconnections.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubconnections.model.Resource
import com.example.githubconnections.model.User
import com.example.githubconnections.repository.UserRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(val userRepository: UserRepository) : ViewModel() {

    val username = MutableLiveData<String>()

    fun getUser(username: String) : LiveData<Resource<User>> {
        this.username.value = username
        return userRepository.loadUser(username)
    }

}