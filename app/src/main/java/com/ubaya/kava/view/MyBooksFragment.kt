package com.ubaya.kava.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.kava.R
import com.ubaya.kava.viewmodel.MyBooksViewModel
import kotlinx.android.synthetic.main.fragment_my_books.*

/**
 * A simple [Fragment] subclass.
 * Use the [MyBooksFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyBooksFragment : Fragment() {
    private lateinit var viewModel: MyBooksViewModel
    private val bookListAdapter = BookListAdapter(arrayListOf(), "My Books")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_books, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(MyBooksViewModel::class.java)
        viewModel.refresh()

        recViewMyBooks.layoutManager = LinearLayoutManager(context)
        recViewMyBooks.adapter = bookListAdapter

        observeViewModel()

        refreshLayoutMyBooks.setOnRefreshListener {
            recViewMyBooks.visibility = View.GONE
            textErrorMyBooks.visibility = View.GONE
            progressLoadMyBooks.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutMyBooks.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.booksLiveData.observe(viewLifecycleOwner) {
            bookListAdapter.updateBookList(it)
        }
        viewModel.booksLoadErrorLiveData.observe(viewLifecycleOwner) {
            textErrorMyBooks.visibility = if (it) View.INVISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) { // sedang loading data
                recViewMyBooks.visibility = View.GONE
                progressLoadMyBooks.visibility = View.VISIBLE
            }
            else {
                recViewMyBooks.visibility = View.VISIBLE
                progressLoadMyBooks.visibility = View.GONE
            }
        }
    }
}