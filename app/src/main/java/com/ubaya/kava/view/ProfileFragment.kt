package com.ubaya.kava.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.kava.R
import com.ubaya.kava.databinding.FragmentProfileBinding
import com.ubaya.kava.model.GlobalData
import com.ubaya.kava.model.User
import com.ubaya.kava.util.loadImage
import com.ubaya.kava.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment(), EditUserListener {
    private lateinit var viewModel: ProfileViewModel
    private lateinit var dataBinding:FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentProfileBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.fetch()

        dataBinding.editListener = this

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.profileLiveData.observe(viewLifecycleOwner) {
            it?.let {
                textProfileUsername.text = it.username
                textProfileName.text = it.name
                textProfileGender.text = it.gender
                textProfilePhone.text = it.phone
                imageProfile.loadImage(400, 400, it.photoURL, progressLoadProfilePhoto)
            }
        }
    }

    override fun onButtonEditClick(view: View) {
        var action = ProfileFragmentDirections.actionEditProfile()
        Navigation.findNavController(view).navigate(action)
    }
}