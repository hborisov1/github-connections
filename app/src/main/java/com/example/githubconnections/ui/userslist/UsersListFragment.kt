package com.example.githubconnections.ui.userslist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.githubconnections.R
import com.example.githubconnections.databinding.FragmentUsersListBinding

class UsersListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding =
            DataBindingUtil.inflate<FragmentUsersListBinding>(inflater, R.layout.fragment_users_list, container, false)
        dataBinding.button.setOnClickListener { v ->
            v.findNavController().navigate(R.id.action_usersListFragment_to_userDetailsFragment)
        }
        return dataBinding.root
    }


}
