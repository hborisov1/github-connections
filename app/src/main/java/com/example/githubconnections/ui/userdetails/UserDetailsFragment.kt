package com.example.githubconnections.ui.userdetails


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
import androidx.navigation.fragment.navArgs
import com.example.githubconnections.AppExecutors
import com.example.githubconnections.R
import com.example.githubconnections.binding.FragmentDataBindingComponent
import com.example.githubconnections.databinding.FragmentUserDetailsBinding
import com.example.githubconnections.di.Injectable
import com.example.githubconnections.ui.common.RepoListAdapter
import com.example.githubconnections.utils.UserUtils
import javax.inject.Inject

class UserDetailsFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    lateinit var userDetailsViewModel: UserDetailsViewModel

    lateinit var dataBinding: FragmentUserDetailsBinding

    private val args: UserDetailsFragmentArgs by navArgs()

    lateinit var adapter : RepoListAdapter

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_user_details,
                container,
                false,
                dataBindingComponent
            )

        dataBinding.textView4.setOnClickListener { v ->
            v.findNavController().navigate(R.id.action_userDetailsFragment_to_usersListFragment)
        }
        dataBinding.textView5.setOnClickListener { v ->
            v.findNavController().navigate(R.id.action_userDetailsFragment_to_usersListFragment)
        }
        dataBinding.buttonTestLogout.setOnClickListener { v ->
            UserUtils(context).setUserLoggedOut()
            v.findNavController().navigate(R.id.action_userDetailsFragment_to_loginFragment)
        }
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        userDetailsViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(UserDetailsViewModel::class.java)
        val username = if (!args.username.isEmpty()) args.username else UserUtils(context).getLoggedUser()
        userDetailsViewModel.setUsername(username)
        dataBinding.user = userDetailsViewModel.user
        dataBinding.setLifecycleOwner(viewLifecycleOwner)

        val reposAdapter = RepoListAdapter(dataBindingComponent,appExecutors)
        dataBinding.recyclerViewRepos.adapter = reposAdapter
        this.adapter = reposAdapter

        initRepoList()
    }

    private fun initRepoList() {
        userDetailsViewModel.repositories.observe(viewLifecycleOwner, Observer { repos ->
            adapter.submitList(repos?.data)
        })
    }

}
