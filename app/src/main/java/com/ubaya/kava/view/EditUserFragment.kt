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
import com.ubaya.kava.databinding.FragmentEditUserBinding
import com.ubaya.kava.model.User
import com.ubaya.kava.viewmodel.ProfileViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [EditUserFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditUserFragment : Fragment(), UserSaveChangesListener,UserGenderClick {
    private lateinit var viewModel:ProfileViewModel
    private lateinit var dataBinding: FragmentEditUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentEditUserBinding.inflate(inflater,container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.fetch()
        observeViewModel()
    }

    override fun inRadioGenderClick(view: View, gender: String, obj: User) {
        obj.gender = gender
    }

    private fun observeViewModel() {
        viewModel.profileLiveData.observe(viewLifecycleOwner){
            dataBinding.user=it
        }
    }

    override fun onSaveChanges(view: View, obj: User) {
        viewModel.update(obj.username,obj.name!!,obj.gender!!,obj.phone!!,obj.photoURL!!)
        Toast.makeText(view.context,"Todo Updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(view).popBackStack()
    }
}