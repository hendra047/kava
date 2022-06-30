package com.ubaya.kava.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ubaya.kava.R
import com.ubaya.kava.databinding.FragmentBookDetailBinding
import com.ubaya.kava.viewmodel.DetailBookViewModel
import kotlinx.android.synthetic.main.fragment_book_detail.*

/**
 * A simple [Fragment] subclass.
 * Use the [BookDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookDetailFragment : Fragment() {
    private lateinit var viewModel: DetailBookViewModel
    private lateinit var dataBinding: FragmentBookDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentBookDetailBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            val bookID = BookDetailFragmentArgs.fromBundle(requireArguments()).bookID
            viewModel = ViewModelProvider(this).get(DetailBookViewModel::class.java)
            viewModel.fetch(bookID)

            observeViewModel()
        }
    }

    private fun observeViewModel() {
        viewModel.bookLD.observe(viewLifecycleOwner) {
            it?.let {
                dataBinding.book = it
            }
        }
    }
}