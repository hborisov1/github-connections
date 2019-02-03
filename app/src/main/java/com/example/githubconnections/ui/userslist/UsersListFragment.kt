package com.example.githubconnections.ui.userslist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.githubconnections.AppExecutors
import com.example.githubconnections.R
import com.example.githubconnections.binding.FragmentDataBindingComponent
import com.example.githubconnections.databinding.FragmentUsersListBinding
import com.example.githubconnections.di.Injectable
import com.example.githubconnections.ui.common.UserListAdapter
import com.example.githubconnections.ui.userdetails.UserDetailsFragmentDirections
import com.example.githubconnections.utils.UserUtils
import javax.inject.Inject

class UsersListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    lateinit var usersListViewModel: UsersListViewModel

    lateinit var dataBinding: FragmentUsersListBinding

    private val args: UsersListFragmentArgs by navArgs()

    lateinit var adapter: UserListAdapter

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_users_list,
                container,
                false,
                dataBindingComponent
            )
        dataBinding.button.setOnClickListener { v ->
            v.findNavController().navigate(R.id.action_usersListFragment_to_userDetailsFragment)
        }
        dataBinding.buttonTestLogout.setOnClickListener { v ->
            UserUtils(context).setUserLoggedOut()
            v.findNavController().navigate(R.id.action_usersListFragment_to_loginFragment)
        }
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        usersListViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(UsersListViewModel::class.java)
        val username = args.username
        val usersType = args.usersType
        usersListViewModel.setUsername(username)
        dataBinding.setLifecycleOwner(viewLifecycleOwner)

        val usersAdapter =
            UserListAdapter(
                dataBindingComponent = dataBindingComponent,
                appExecutors = appExecutors
            ) { user ->
                findNavController().navigate(UsersListFragmentDirections.actionUsersListFragmentToUserDetailsFragment(user.username))
            }
        dataBinding.recyclerViewUsers.adapter = usersAdapter
        this.adapter = usersAdapter

        initUserList()
    }

    private fun initUserList() {
        usersListViewModel.users.observe(viewLifecycleOwner, Observer { users ->
            adapter.submitList(users?.data)
        })
    }


}
