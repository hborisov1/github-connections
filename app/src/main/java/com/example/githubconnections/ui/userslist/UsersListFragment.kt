package com.example.githubconnections.ui.userslist


import android.os.Bundle
import android.view.*
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
import com.example.githubconnections.api.ApiSuccessResponse
import com.example.githubconnections.binding.FragmentDataBindingComponent
import com.example.githubconnections.databinding.FragmentUsersListBinding
import com.example.githubconnections.di.Injectable
import com.example.githubconnections.ui.common.UserListAdapter
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
        setHasOptionsMenu(true)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        usersListViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(UsersListViewModel::class.java)
        val username = args.username
        val usersType = args.usersType
        usersListViewModel.setSearchPair(Pair(username, usersType))
        dataBinding.setLifecycleOwner(viewLifecycleOwner)

        val usersAdapter =
            UserListAdapter(
                dataBindingComponent = dataBindingComponent,
                appExecutors = appExecutors
            ) { user ->
                findNavController().navigate(
                    UsersListFragmentDirections.actionUsersListFragmentToUserDetailsFragment(
                        user.username
                    )
                )
            }
        dataBinding.recyclerViewUsers.adapter = usersAdapter
        this.adapter = usersAdapter

        initUserList()
    }

    private fun initUserList() {
        usersListViewModel.users.observe(viewLifecycleOwner, Observer { users ->
            if (users is ApiSuccessResponse) {
                adapter.submitList(users.body)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.acion_logout -> {
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logout() {
        UserUtils(context).setUserLoggedOut()
        findNavController().navigate(R.id.action_usersListFragment_to_loginFragment)
    }

}
