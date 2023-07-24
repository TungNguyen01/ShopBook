package com.example.shopbook.ui.main.search.searchHistory

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopbook.R

class SearchHistoryFragment : Fragment() {

    companion object {
        fun newInstance() = SearchHistoryFragment()
    }

    private lateinit var viewModel: SearchHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_search_history, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchHistoryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}