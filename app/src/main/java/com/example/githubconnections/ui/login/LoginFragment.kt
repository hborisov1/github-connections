package com.example.githubconnections.ui.login


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.githubconnections.R
import com.example.githubconnections.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding =
            DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)
        dataBinding.buttonLogin.setOnClickListener { v ->
            v.findNavController().navigate(R.id.action_loginFragment_to_userDetailsFragment)
        }
        return dataBinding.root
    }


}
