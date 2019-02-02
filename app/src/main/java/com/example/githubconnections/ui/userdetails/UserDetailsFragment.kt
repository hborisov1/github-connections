package com.example.githubconnections.ui.userdetails


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.githubconnections.R
import com.example.githubconnections.databinding.FragmentUserDetailsBinding
import com.example.githubconnections.utils.SharedPrefsUtils

class UserDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding =
            DataBindingUtil.inflate<FragmentUserDetailsBinding>(
                inflater,
                R.layout.fragment_user_details,
                container,
                false
            )

        dataBinding.textView4.setOnClickListener { v ->
            v.findNavController().navigate(R.id.action_userDetailsFragment_to_usersListFragment)
        }
        dataBinding.textView5.setOnClickListener { v ->
            v.findNavController().navigate(R.id.action_userDetailsFragment_to_usersListFragment)
        }
        dataBinding.buttonTestLogout.setOnClickListener { v ->
            SharedPrefsUtils(context).setUserLoggedOut()
            v.findNavController().navigate(R.id.action_userDetailsFragment_to_loginFragment)
        }
        return dataBinding.root
    }

}
