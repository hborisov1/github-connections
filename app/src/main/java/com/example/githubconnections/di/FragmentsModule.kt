package com.example.githubconnections.di

import com.example.githubconnections.ui.login.LoginFragment
import com.example.githubconnections.ui.userdetails.UserDetailsFragment
import com.example.githubconnections.ui.userslist.UsersListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentsModule {
    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeUserDetailsFragment(): UserDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeUsersListFragment(): UsersListFragment
}
