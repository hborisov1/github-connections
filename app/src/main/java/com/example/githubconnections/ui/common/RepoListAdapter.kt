package com.example.githubconnections.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.example.githubconnections.AppExecutors
import com.example.githubconnections.R
import com.example.githubconnections.databinding.ListItemRepoBinding
import com.example.githubconnections.model.Repo

class RepoListAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors
) : DataBoundListAdapter<Repo, ListItemRepoBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Repo>() {
        override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
            return oldItem.owner == newItem.owner
                    && oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
            return oldItem.description == newItem.description
                    && oldItem.forksCount == newItem.forksCount
        }
    }
) {

    override fun createBinding(parent: ViewGroup): ListItemRepoBinding {
        val binding = DataBindingUtil.inflate<ListItemRepoBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item_repo,
            parent,
            false,
            dataBindingComponent
        )
        return binding
    }

    override fun bind(binding: ListItemRepoBinding, item: Repo) {
        binding.repo = item
    }
}
