package com.ubaya.kava.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.ubaya.kava.R
import com.ubaya.kava.databinding.FragmentLoginBinding
import com.ubaya.kava.databinding.FragmentProfileBinding
import com.ubaya.kava.model.GlobalData
import com.ubaya.kava.model.User
import com.ubaya.kava.viewmodel.LoginViewModel
import com.ubaya.kava.viewmodel.ProfileViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(), LoginListener, RegisterListener {
    private lateinit var viewModel: LoginViewModel
    private lateinit var dataBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        dataBinding.user = User("", "", "", "", "")
        dataBinding.loginListener = this
        dataBinding.registerListener = this
    }

    private fun observeViewModel(v: View) {
        viewModel.loginLiveData.observe(viewLifecycleOwner){
            it?.let {
                if (it.username != "") {
                    GlobalData.username = it.username
                    val action = LoginFragmentDirections.actionLoginToHome()
                    Navigation.findNavController(v).navigate(action)
                } else {
                    Toast.makeText(v.context, "Username or Password is wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onLoginClick(v: View, user: User) {
        viewModel.checkLogin(user)
        observeViewModel(v)
    }

    override fun onRegisterClick(v: View) {
        val action = LoginFragmentDirections.actionLoginToRegister()
        Navigation.findNavController(v).navigate(action)
    }
}