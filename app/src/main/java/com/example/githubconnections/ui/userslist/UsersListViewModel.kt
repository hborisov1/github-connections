package com.example.githubconnections.ui.userslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.githubconnections.model.Resource
import com.example.githubconnections.model.User
import com.example.githubconnections.repository.UserRepository
import com.example.githubconnections.utils.AbsentLiveData
import javax.inject.Inject

class UsersListViewModel @Inject constructor(userRepository: UserRepository) : ViewModel() {

    private val _username = MutableLiveData<String>()
    val username: LiveData<String>
        get() = _username
    val users: LiveData<Resource<List<User>>> = Transformations
        .switchMap(_username) { username ->
            if (username == null) {
                AbsentLiveData.create()
            } else {
                userRepository.loadFollowers(username) //TODO add following too
            }
        }

    fun setUsername(username: String?) {
        if (_username.value != username) {
            _username.value = username
        }
    }

}