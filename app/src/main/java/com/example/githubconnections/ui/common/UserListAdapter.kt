package com.example.githubconnections.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.example.githubconnections.AppExecutors
import com.example.githubconnections.R
import com.example.githubconnections.databinding.ListItemUserBinding
import com.example.githubconnections.model.User

class UserListAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val userClickCallback: ((User) -> Unit)?
) : DataBoundListAdapter<User, ListItemUserBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.avatarUrl == newItem.avatarUrl
                    && oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.bio == newItem.bio
                    && oldItem.location == newItem.location
        }
    }
) {

    override fun createBinding(parent: ViewGroup): ListItemUserBinding {
        val binding = DataBindingUtil.inflate<ListItemUserBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item_user,
            parent,
            false,
            dataBindingComponent
        )
        binding.root.setOnClickListener {
            binding.user?.let {
                userClickCallback?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: ListItemUserBinding, item: User) {
        binding.user = item
    }
}
