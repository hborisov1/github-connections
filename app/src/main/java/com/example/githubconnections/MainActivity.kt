package com.example.githubconnections

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
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
        )
        navController.addOnDestinationChangedListener { _, destination, arguments ->
            when (destination.id) {
                R.id.loginFragment -> supportActionBar?.title = getString(R.string.fragment_label_login)
                R.id.userDetailsFragment -> supportActionBar?.title = getString(R.string.fragment_label_user_details)
                R.id.usersListFragment -> supportActionBar?.title =
                    if (arguments?.getString("usersType", "followers").equals("following"))//TODO hardcoded strings...
                        getString(R.string.fragment_label_users_list_following)
                    else
                        getString(R.string.fragment_label_users_list_followers)
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
