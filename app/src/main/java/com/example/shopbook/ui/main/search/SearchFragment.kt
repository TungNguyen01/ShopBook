package com.example.shopbook.ui.main.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopbook.R
import com.example.shopbook.adapter.BookAdapter
import com.example.shopbook.databinding.FragmentSearchBinding
import com.example.shopbook.ui.main.wishlist.viewmodel.WishlistViewModel
import com.example.shopbook.ui.productdetail.ProductdetailFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class SearchFragment : Fragment() {
    private var binding: FragmentSearchBinding? = null
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        val bookList = viewModel.getProducts()
        val adapter = BookAdapter(bookList)
        binding?.recyclerProduct?.layoutManager = GridLayoutManager(context, 2)
        binding?.recyclerProduct?.adapter = adapter
        adapter.setOnItemClickListener(object : BookAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val productFragment = ProductdetailFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, productFragment)
                    .addToBackStack("SearchFragment")
                    .commit()
            }
        })
        val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.visibility = View.VISIBLE
    }
}