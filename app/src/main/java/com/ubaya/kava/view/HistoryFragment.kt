package com.ubaya.kava.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.kava.R
import com.ubaya.kava.viewmodel.HistoryViewModel
import kotlinx.android.synthetic.main.fragment_history.*

/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : Fragment() {
    private lateinit var viewModel: HistoryViewModel
    private val bookListAdapter = BookListAdapter(arrayListOf(), "History")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)
        viewModel.refresh()

        recViewHistory.layoutManager = LinearLayoutManager(context)
        recViewHistory.adapter = bookListAdapter

        observeViewModel()

        refreshLayoutHistory.setOnRefreshListener {
            recViewHistory.visibility = View.GONE
            textErrorHistory.visibility = View.GONE
            progressLoadHistory.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutHistory.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.booksLiveData.observe(viewLifecycleOwner) {
            bookListAdapter.updateBookList(it)
        }
        viewModel.booksLoadErrorLiveData.observe(viewLifecycleOwner) {
            textErrorHistory.visibility = if (it) View.INVISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) { // sedang loading data
                recViewHistory.visibility = View.GONE
                progressLoadHistory.visibility = View.VISIBLE
            }
            else {
                recViewHistory.visibility = View.VISIBLE
                progressLoadHistory.visibility = View.GONE
            }
        }
    }
}