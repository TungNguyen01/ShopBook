package com.example.shopbook.ui.main.home


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopbook.R
import com.example.shopbook.data.model.*
import com.example.shopbook.databinding.FragmentHomeBinding
import com.example.shopbook.ui.category.categoryindex.CategoryIndexFragment
import com.example.shopbook.ui.main.adapter.BookAdapter
import com.example.shopbook.ui.main.adapter.CategoryAdapter
import com.example.shopbook.ui.main.adapter.DiscoverStoreAdapter
import com.example.shopbook.ui.main.adapter.NewArrivalAdapter
import com.example.shopbook.ui.main.home.viewmodel.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeFragment : Fragment() {
    private lateinit var adapter: BookAdapter
    private var binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel
    private var bookList = mutableListOf<HotBook>()
    private var categoryList = mutableListOf<Category>()
    private var newbookList = mutableListOf<NewArrival>()
    private var author = mutableListOf<Author>()
    private lateinit var hotBooksAdapter: BookAdapter
    private lateinit var categoriesAdapter: CategoryAdapter
    private lateinit var newBooksAdapter: NewArrivalAdapter
    private lateinit var authorsAdapter: DiscoverStoreAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.visibility = View.VISIBLE
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        hotBooksAdapter = BookAdapter()
        categoriesAdapter = CategoryAdapter()
        newBooksAdapter = NewArrivalAdapter()
        authorsAdapter = DiscoverStoreAdapter()

        viewModel.getAllHotBook()
        viewModel.getAllCategory()
        viewModel.getAllNewBook()
        viewModel.getAllAuthor()

        viewModel.hotBookList.observe(viewLifecycleOwner, { hotBooks ->
            hotBooksAdapter.updateData(hotBooks)
        })

        viewModel.categoryList.observe(viewLifecycleOwner, { categories ->
            categoriesAdapter.updateData(categories)
        })

        viewModel.newbookList.observe(viewLifecycleOwner, { newBooks ->
            newBooksAdapter.updateData(newBooks)
        })

        viewModel.authorList.observe(viewLifecycleOwner, { authors ->
            authorsAdapter.updateData(authors)
        })
        // Log.d("tung", bookList.toString())
        binding.recyclerviewHotbook.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = hotBooksAdapter
        }

        binding.recyclerviewCategory.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoriesAdapter
        }

        binding.recyclerviewNewarrival.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = newBooksAdapter
        }

        binding.recyclerviewDiscoverstore.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = authorsAdapter
        }
        binding.img1.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, CategoryIndexFragment())
                .addToBackStack("HomeFragment")
                .commit()
        }
        return binding.root
    }
}

