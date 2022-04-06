package com.ubaya.ulib160419002.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.ulib160419002.R
import com.ubaya.ulib160419002.util.loadImage
import com.ubaya.ulib160419002.viewmodel.ListBookViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var viewModel: ListBookViewModel
    private val bookListAdapter = BookListAdapter(arrayListOf(), "Home")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(ListBookViewModel::class.java)
        viewModel.refresh()

        recViewHome.layoutManager = LinearLayoutManager(context)
        recViewHome.adapter = bookListAdapter

        observeViewModel()

        refreshLayoutHome.setOnRefreshListener {
            recViewHome.visibility = View.GONE
            textErrorHome.visibility = View.GONE
            progressLoadHome.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayoutHome.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.booksLiveData.observe(viewLifecycleOwner) {
            bookListAdapter.updateBookList(it)
        }
        viewModel.booksLoadErrorLiveData.observe(viewLifecycleOwner) {
            textErrorHome.visibility = if (it) View.INVISIBLE else View.GONE
        }
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) { // sedang loading data
                recViewHome.visibility = View.GONE
                progressLoadHome.visibility = View.VISIBLE
            }
            else {
                recViewHome.visibility = View.VISIBLE
                progressLoadHome.visibility = View.GONE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_top, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, view!!.findNavController()) ||
                super.onOptionsItemSelected(item)
    }
}