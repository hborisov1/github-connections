package com.example.githubconnections.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubconnections.ui.login.LoginViewModel
import com.example.githubconnections.ui.userdetails.UserDetailsViewModel
import com.example.githubconnections.ui.userslist.UsersListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailsViewModel::class)
    abstract fun bindUserDetailsViewModel(userDetailsViewModel: UserDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UsersListViewModel::class)
    abstract fun bindUsersListViewModel(usersListViewModel: UsersListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: GitHubViewModelFactory): ViewModelProvider.Factory
}
