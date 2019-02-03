package com.example.githubconnections.ui.userdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.githubconnections.model.Repo
import com.example.githubconnections.model.Resource
import com.example.githubconnections.model.User
import com.example.githubconnections.repository.RepoRepository
import com.example.githubconnections.repository.UserRepository
import com.example.githubconnections.utils.AbsentLiveData
import javax.inject.Inject


class UserDetailsViewModel @Inject constructor(userRepository: UserRepository, repoRepository: RepoRepository) :
    ViewModel() {
    private val _username = MutableLiveData<String>()
    val username: LiveData<String>
        get() = _username
    val repositories: LiveData<Resource<List<Repo>>> = Transformations
        .switchMap(_username) { username ->
            if (username == null) {
                AbsentLiveData.create()
            } else {
                repoRepository.loadRepos(username)
            }
        }
    val user: LiveData<Resource<User>> = Transformations
        .switchMap(_username) { username ->
            if (username == null) {
                AbsentLiveData.create()
            } else {
                userRepository.loadUser(username)
            }
        }

    fun setUsername(username: String?) {
        if (_username.value != username) {
            _username.value = username
        }
    }

}