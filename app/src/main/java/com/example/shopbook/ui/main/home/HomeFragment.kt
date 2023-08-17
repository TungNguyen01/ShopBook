package com.example.shopbook.ui.main.home


import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.example.shopbook.ui.author.AuthorFragment
import com.example.shopbook.ui.category.CategoryBookFragment
import com.example.shopbook.ui.category.categoryindex.CategoryIndexFragment
import com.example.shopbook.ui.main.adapter.*
import com.example.shopbook.ui.main.home.viewmodel.HomeViewModel
import com.example.shopbook.ui.productdetail.ProductdetailFragment
import com.example.shopbook.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.random.Random


class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var hotBooksAdapter: BookAdapter
    private lateinit var categoriesAdapter: CategoryAdapter
    private lateinit var newBooksAdapter: NewArrivalAdapter
    private lateinit var authorsAdapter: DiscoverStoreAdapter
    private lateinit var bannerAdapter: BanerAdapter
    val baner = ArrayList<String>()
    private var pastPage = -1
    private var currentPage = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        hotBooksAdapter = BookAdapter()
        categoriesAdapter = CategoryAdapter()
        newBooksAdapter = NewArrivalAdapter()
        authorsAdapter = DiscoverStoreAdapter()
        bannerAdapter = BanerAdapter()
        viewModel.getAllHotBook()
        viewModel.getAllCategory()
        viewModel.getAllNewBook()
        viewModel.getAllAuthor()
        viewModel.getBanner()
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
        viewModel.banner.observe(viewLifecycleOwner, {banner ->
            bannerAdapter.updateData(banner)
        })
        binding?.apply {
            imageProfile.setOnClickListener {
                val profileFragment = ProfileFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, profileFragment)
                    .addToBackStack("HomeFragment")
                    .commit()
            }
        }
        binding.viewPagerBaner.adapter = bannerAdapter
        binding.viewPagerBaner.isUserInputEnabled = false

        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {
                val random = (0..2).random()
                binding.viewPagerBaner.setCurrentItem(
                    random
                )
                mainHandler.postDelayed(this, 3000)
            }
        })
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
                            R.id.container,
                            ProductdetailFragment().apply { arguments = bundle })
                        .addToBackStack("HomeFragment")
                        .commit()
                    pastPage = currentPage
                }
            })
        }

        binding.recyclerviewCategory.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = categoriesAdapter
            categoriesAdapter.setOnItemClickListener(object : OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val bundle = Bundle()
                    val categoryId = categoriesAdapter.getCategory(position).categoryId
                    val categoryName = categoriesAdapter.getCategory(position).name
                    bundle.putString("categoryId", categoryId.toString())
                    bundle.putString("categoryName", categoryName)
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, CategoryBookFragment()
                            .apply { arguments = bundle })
                        .addToBackStack("CategoryIndex")
                        .commit()
                }
            })
        }

        binding.recyclerviewNewarrival.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = newBooksAdapter
            newBooksAdapter.setOnItemClickListener(object : OnItemClickListener {
                override fun onItemClick(position: Int) {
                    val product = newBooksAdapter.getBook(position)
                    val bundle = Bundle()
                    bundle.putString("bookId", product.product_id.toString())
                    parentFragmentManager.beginTransaction()
                        .replace(
                            R.id.container,
                            ProductdetailFragment().apply { arguments = bundle })
                        .addToBackStack("HomeFragment")
                        .commit()
                    pastPage = currentPage
                }
            })
        }

        binding.recyclerviewDiscoverstore.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = authorsAdapter
            authorsAdapter.setOnItemClickListener(object :OnItemClickListener{
                override fun onItemClick(position: Int) {
                    val author = authorsAdapter.getAuthor(position)
                    val bundle = Bundle()
                    bundle.putString("authorId", author.authorId.toString())
                    parentFragmentManager.beginTransaction().replace(
                        R.id.container,
                        AuthorFragment().apply {arguments = bundle })
                        .addToBackStack("HomeFragment")
                        .commit()
                    pastPage = currentPage
                }
            })
        }
        binding.img1.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, CategoryIndexFragment())
                .addToBackStack("HomeFragment")
                .commit()
        }
        return binding.root
    }

}

