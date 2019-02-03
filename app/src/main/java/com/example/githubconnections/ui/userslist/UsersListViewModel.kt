package com.example.githubconnections.ui.userslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.githubconnections.api.ApiResponse
import com.example.githubconnections.model.User
import com.example.githubconnections.repository.UserRepository
import com.example.githubconnections.utils.AbsentLiveData
import javax.inject.Inject

class UsersListViewModel @Inject constructor(userRepository: UserRepository) : ViewModel() {

    private val _searchPair = MutableLiveData<Pair<String, String>>()//first is username, second is userType
    val searchPair: LiveData<Pair<String, String>>
        get() = _searchPair
    val users: LiveData<ApiResponse<List<User>>> = Transformations
        .switchMap(_searchPair) { searchPair ->
            if (searchPair == null) {
                AbsentLiveData.create()
            } else {
                userRepository.loadFollowers(searchPair.first, searchPair.second)
            }
        }

    fun setSearchPair(searchPair: Pair<String, String>?) {
        if (_searchPair.value != searchPair) {
            _searchPair.value = searchPair
        }
    }

}