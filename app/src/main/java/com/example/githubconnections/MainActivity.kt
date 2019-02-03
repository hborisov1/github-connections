package com.example.githubconnections

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.githubconnections.databinding.ActivityMainBinding
import com.example.githubconnections.utils.UserUtils
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val toolbar: Toolbar = dataBinding.toolbar
        setSupportActionBar(toolbar)

        val navController = findNavController(R.id.fragmentContainer)
        val appBarConfig = AppBarConfiguration(
            setOf(
                R.id.loginFragment,
                R.id.userDetailsFragment,
                R.id.usersListFragment
            )
        ) // TODO leave only loginFragmentHere, after fixing the bug with up button for current user
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                // TODO set dynamic titles (get them from arguments)
                R.id.loginFragment -> supportActionBar?.title = getString(R.string.fragment_label_login)
                R.id.userDetailsFragment -> supportActionBar?.title = getString(R.string.fragment_label_user_details)
                R.id.usersListFragment -> supportActionBar?.title = getString(R.string.fragment_label_users_list)
                // TODO when destination is UserDetailsFragment and title is the same as current user - set userDetailsFragment as AppBarConfiguration home
                // this could cause potential bug, when you find the current user in someones followers/following
            }
        }
        toolbar.setupWithNavController(navController, appBarConfig)

        // TODO this causes clearing of the EditText in LoginFragment on orientation change, should be fixed
        if (!UserUtils(this).isLoggedIn()) {
            navController.popBackStack()
            navController.navigate(R.id.loginFragment)
        }
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

}
