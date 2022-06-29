package com.ubaya.ulib160419002.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ubaya.ulib160419002.R
import com.ubaya.ulib160419002.viewmodel.DetailBookViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [EditBookFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditBookFragment : Fragment() {
    private lateinit var viewModel: DetailBookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }


}