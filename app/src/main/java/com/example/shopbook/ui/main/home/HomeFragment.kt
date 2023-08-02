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
import com.example.shopbook.ui.main.MainMenuFragment
import com.example.shopbook.ui.main.adapter.BookAdapter
import com.example.shopbook.ui.main.adapter.CategoryAdapter
import com.example.shopbook.ui.main.adapter.DiscoverStoreAdapter
import com.example.shopbook.ui.main.adapter.NewArrivalAdapter
import com.example.shopbook.ui.main.home.viewmodel.HomeViewModel
import com.example.shopbook.ui.productdetail.ProductdetailFragment
import com.example.shopbook.ui.profile.ProfileFragment


class HomeFragment : Fragment() {
    private lateinit var adapter: com.example.shopbook.ui.adapter.BookAdapter
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
    private lateinit var profileImage : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
//        val view = inflater.inflate(R.layout.fragment_home, container, false)

//        profileImage=view.findViewById(R.id.image_profile)
//        profileImage.setOnClickListener{
//            navigateToProfileFragment()
//        }
        hotBooksAdapter = BookAdapter()
        categoriesAdapter = CategoryAdapter()
        newBooksAdapter = NewArrivalAdapter()
        authorsAdapter = DiscoverStoreAdapter()

        viewModel.getAllHotBook()
        viewModel.getAllCategory()
        viewModel.getAllNewBook()
        viewModel.getAllAuthor()
        viewModel.getProductInfo(id)

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
        return binding.root
    }
//    fun navigateToProfileFragment() {
//        val fragment = ProfileFragment()
//        val fragmentManager = requireActivity().supportFragmentManager
//        val transaction = fragmentManager.beginTransaction()
//        transaction.replace(R.id.container, fragment)
//        transaction.addToBackStack("")
//        transaction.commit()
//    }
//    private fun observeProducts() {
//        viewModel.hotBookList.observe(viewLifecycleOwner, Observer { ho ->
//            if (productList != null) {
//                adapter = com.example.shopbook.ui.adapter.BookAdapter(productList)
//               // binding?.recyclerProduct?.adapter = adapter
//              //  binding?.loadingLayout?.root?.visibility=View.INVISIBLE
//                navToProductDetail()
//            } else {
//                Log.d("NULLLL", "HEllo")
//            }
//        })
//    }

    private fun navToProductDetail() {
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val product = adapter.getBook(position)
                val bundle = Bundle()
                bundle.putString("bookId", product.product_id.toString())
                parentFragmentManager.beginTransaction()
                    .replace(
                        R.id.frame_layout,
                        ProductdetailFragment().apply { arguments = bundle })
                    .addToBackStack("SearchFragment")
                    .commit()
            }
        })
    }
}

