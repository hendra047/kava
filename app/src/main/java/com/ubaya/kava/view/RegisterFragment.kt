package com.ubaya.kava.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.kava.R
import com.ubaya.kava.databinding.FragmentRegisterBinding
import com.ubaya.kava.model.User
import com.ubaya.kava.viewmodel.ProfileViewModel
import com.ubaya.kava.viewmodel.RegisterViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment(), AddUserListener, UserGenderClick{
    private lateinit var viewModel:RegisterViewModel
    private lateinit var dataBinding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_register, container, false)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.user = User("","","","","","")
        viewModel= ViewModelProvider(this).get(RegisterViewModel::class.java)

        dataBinding.registerListener = this
        dataBinding.genderListener = this
    }
    override fun onButtonRegisterClick(v: View) {
        dataBinding.user?.let {
            val list = listOf(it)
            viewModel.addUser(list)
            Toast.makeText(v.context, "User Created", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(v).popBackStack()
        }
    }

    override fun onRadioGenderClick(view: View, gender: String, obj: User) {
        obj.gender = gender
    }
}