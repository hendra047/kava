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
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.ubaya.kava.R
import com.ubaya.kava.databinding.FragmentCreateBookBinding
import com.ubaya.kava.model.Book
import com.ubaya.kava.viewmodel.DetailBookViewModel
import java.util.concurrent.TimeUnit


class CreateBookFragment : Fragment(), AddBookClickListener {
    private lateinit var viewModel:DetailBookViewModel
    private lateinit var dataBinding:FragmentCreateBookBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_create_book, container, false)
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_book,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.book = Book("","","",0, "","","","",0.0,"",0)
        viewModel = ViewModelProvider(this).get(DetailBookViewModel::class.java)
        dataBinding.listener = this
    }

    override fun onButtonAddBook(v: View) {
        dataBinding.book?.let {
            val list = listOf(it)
            viewModel.addBook(list)
            Toast.makeText(v.context, "Book created", Toast.LENGTH_SHORT).show()
        }
    }

}