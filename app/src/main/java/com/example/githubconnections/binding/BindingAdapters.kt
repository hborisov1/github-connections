package com.example.githubconnections.binding

import androidx.databinding.BindingAdapter
import android.view.View

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }
}
