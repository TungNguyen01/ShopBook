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
import com.example.shopbook.ui.adapter.OnItemClickListener
import com.example.shopbook.ui.category.CategoryBookFragment
import com.example.shopbook.ui.category.categoryindex.CategoryIndexFragment
import com.example.shopbook.ui.main.adapter.BookAdapter
import com.example.shopbook.ui.main.adapter.CategoryAdapter
import com.example.shopbook.ui.main.adapter.DiscoverStoreAdapter
import com.example.shopbook.ui.main.adapter.NewArrivalAdapter
import com.example.shopbook.ui.main.home.viewmodel.HomeViewModel
import com.example.shopbook.ui.productdetail.ProductdetailFragment
import com.example.shopbook.ui.profile.ProfileFragment
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
    private var pastPage = -1
    private var currentPage = 1

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
//        .setOnClickListener {
//            val profileFragment = ProfileFragment()
//            parentFragmentManager.beginTransaction()
//                .replace(R.id.frame_layout, profileFragment)
//                .addToBackStack("productFragment")
//                .commit()
//        }

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
        binding?.apply {
            imageProfile.setOnClickListener {
            val profileFragment = ProfileFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, profileFragment)
                .addToBackStack("productFragment")
                .commit()
            }
        }
        binding.recyclerviewHotbook.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = hotBooksAdapter
            hotBooksAdapter.setOnItemClickListener(object : OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val product = hotBooksAdapter.getBook(position)
                    val bundle = Bundle()
                    bundle.putString("bookId", product.product_id.toString())
                    parentFragmentManager.beginTransaction()
                        .replace(
                            R.id.frame_layout,
                            ProductdetailFragment().apply { arguments = bundle })
                        .addToBackStack("SearchFragment")
                        .commit()
                    pastPage = currentPage
                }
            })
        }

        binding.recyclerviewCategory.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoriesAdapter
            categoriesAdapter.setOnItemClickListener(object : OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val bundle = Bundle()
                    val categoryId = categoriesAdapter.getCategory(position).categoryId
                    bundle.putString("categoryId", categoryId.toString())
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, CategoryBookFragment()
                            .apply { arguments = bundle })
                        .addToBackStack("CategoryIndex")
                        .commit()
                }
            })
        }

        binding.recyclerviewNewarrival.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = newBooksAdapter
            newBooksAdapter.setOnItemClickListener(object : OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val product = newBooksAdapter.getBook(position)
                    val bundle = Bundle()
                    bundle.putString("bookId", product.product_id.toString())
                    parentFragmentManager.beginTransaction()
                        .replace(
                            R.id.frame_layout,
                            ProductdetailFragment().apply { arguments = bundle })
                        .addToBackStack("SearchFragment")
                        .commit()
                    pastPage = currentPage
                }
            })
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

