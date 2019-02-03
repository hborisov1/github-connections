package com.example.githubconnections.ui.login


import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.githubconnections.R
import com.example.githubconnections.databinding.FragmentLoginBinding
import com.example.githubconnections.di.Injectable
import com.example.githubconnections.model.Status
import com.example.githubconnections.model.User
import com.example.githubconnections.utils.UserUtils
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class LoginFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var loginViewModel: LoginViewModel

    private lateinit var dataBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        dataBinding.buttonLogin.setOnClickListener { v -> performLogin(v) }
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loginViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(LoginViewModel::class.java)
    }

    private fun performLogin(v: View) {
        val username = dataBinding.editTextUsername.text.toString()
        if (username.isEmpty()) {
            dataBinding.editTextUsername.error = getString(R.string.error_username_empty)
        } else {
            dismissKeyboard(v.windowToken)
            loginViewModel.getUser(username).observe(viewLifecycleOwner, Observer { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        dataBinding.loading = false
                        if (result.data == null) {
                            showSnackbarError(v, R.string.error_unexpected_error)
                        } else {
                            onSuccessfulLogin(v, result.data)
                        }
                    }
                    Status.ERROR -> {
                        dataBinding.loading = false
                        showSnackbarError(v, R.string.error_not_found) // TODO handle exceeded API rate limit error...
                    }
                    Status.LOADING -> dataBinding.loading = true

                }
            })
        }
    }

    private fun onSuccessfulLogin(v: View, user: User) {
        UserUtils(context).setUserLoggedIn(user.username)
        val action = LoginFragmentDirections.actionLoginFragmentToUserDetailsFragment(user.username)
        v.findNavController().navigate(action)
    }

    private fun dismissKeyboard(windowToken: IBinder) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun showSnackbarError(v: View, errorResId: Int) {
        Snackbar.make(v, errorResId, Snackbar.LENGTH_LONG).show()
    }

}
