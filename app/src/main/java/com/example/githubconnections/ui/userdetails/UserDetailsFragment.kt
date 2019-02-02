package com.example.githubconnections.ui.userdetails


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.githubconnections.R
import com.example.githubconnections.databinding.FragmentUserDetailsBinding

class UserDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding =
            DataBindingUtil.inflate<FragmentUserDetailsBinding>(inflater, R.layout.fragment_user_details, container, false)
        return dataBinding.root
    }


}
