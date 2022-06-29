package com.ubaya.kava.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.kava.R
import com.ubaya.kava.viewmodel.BookmarkViewModel
import kotlinx.android.synthetic.main.fragment_bookmark.*

/**
 * A simple [Fragment] subclass.
 * Use the [BookmarkFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookmarkFragment : Fragment() {
    private lateinit var viewModel: BookmarkViewModel
    private val bookListAdapter = BookListAdapter(arrayListOf(), "Bookmark")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(BookmarkViewModel::class.java)
        viewModel.refresh()

        recViewBookmark.layoutManager = LinearLayoutManager(context)
        recViewBookmark.adapter = bookListAdapter

        observeViewModel()

        refreshLayoutBookmark.setOnRefreshListener {
            recViewBookmark.visibility = View.GONE
            textErrorBookmark.visibility = View.GONE
            progressLoadBookmark.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutBookmark.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.booksLiveData.observe(viewLifecycleOwner) {
            bookListAdapter.updateBookList(it)
        }
        viewModel.booksLoadErrorLiveData.observe(viewLifecycleOwner) {
            textErrorBookmark.visibility = if (it) View.INVISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) { // sedang loading data
                recViewBookmark.visibility = View.GONE
                progressLoadBookmark.visibility = View.VISIBLE
            }
            else {
                recViewBookmark.visibility = View.VISIBLE
                progressLoadBookmark.visibility = View.GONE
            }
        }
    }
}