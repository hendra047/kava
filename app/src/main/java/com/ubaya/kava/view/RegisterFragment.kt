package com.ubaya.kava.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.kava.R
import com.ubaya.kava.databinding.FragmentRegisterBinding
import com.ubaya.kava.viewmodel.ProfileViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment(), AddUserListener{
    private lateinit var viewModel:ProfileViewModel
    private lateinit var dataBinding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProvider(this).get(ProfileViewModel::class.java)

        dataBinding.registerListener = this
    }
    override fun onButtonRegisterClick(v: View) {
        dataBinding.user?.let {
            val list = listOf(it)
            viewModel.addUser(list)
            Toast.makeText(v.context, "User Created", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(v).popBackStack()
        }
    }
}