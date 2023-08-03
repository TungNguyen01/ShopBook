package com.example.shopbook.ui.category

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopbook.R
import com.example.shopbook.data.model.Product
import com.example.shopbook.databinding.FragmentCategoryBookBinding
import com.example.shopbook.ui.adapter.BookAdapter
import com.example.shopbook.ui.adapter.OnItemClickListener
import com.example.shopbook.ui.productdetail.ProductdetailFragment
import com.example.shopbook.utils.ItemSpacingDecoration

class CategoryBookFragment : Fragment() {

    companion object {
        fun newInstance() = CategoryBookFragment()
    }

    private lateinit var viewModel: CategoryBookViewModel
    private var binding: FragmentCategoryBookBinding? = null
    private lateinit var adapter: BookAdapter
    private var bookList = mutableListOf<Product>()
    private var currentPage = 1
    private var lastPosition = 0
    private var totalPosition = 0
    private var currentPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CategoryBookViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCategoryBookBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.loadingLayout?.root?.visibility = View.VISIBLE
        adapter = BookAdapter()
        val categoryId = arguments?.getString("categoryId")?.toInt()
        categoryId?.let {
            viewModel.getProductsInCategory(it, 10, 1, 100)
            loadData(categoryId)
        }
        observeProducts()
        val horizontalSpacing =
            resources.getDimensionPixelSize(R.dimen.horizontal_spacing)
        val verticalSpacing =
            resources.getDimensionPixelSize(R.dimen.vertical_spacing)
        binding?.apply {
            recyclerCategory.addItemDecoration(
                ItemSpacingDecoration(
                    horizontalSpacing,
                    verticalSpacing
                )
            )
            recyclerCategory.layoutManager = GridLayoutManager(context, 2)
            recyclerCategory.adapter = adapter
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
    }

    private fun navToProductDetail() {
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val productFragment = ProductdetailFragment()
                val product = adapter.getBook(position)
                val bundle = Bundle()
                bundle.putString("bookId", product.product_id.toString())
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, productFragment.apply { arguments = bundle })
                    .addToBackStack("CategoryBookFragment")
                    .commit()
            }
        })
    }

    private fun addItemToCart() {
        adapter.setAddItemToCart(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val product = adapter.getBook(position)
                viewModel.addItemToCart(product.product_id)
                Toast.makeText(context, "ADD ITEM TO CART SUCCESSFUL", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun observeProducts() {
        viewModel.producList.observe(viewLifecycleOwner, Observer { productList ->
            Log.d("PRODUCTT", productList.toString())
            if (productList != null) {
//                    adapter.setData(productList)
                bookList.addAll(productList)
                adapter.setData(bookList)
                navToProductDetail()
                addItemToCart()
                binding?.loadingLayout?.root?.visibility = View.INVISIBLE
            }
        })
    }

    private fun loadData(categoryId: Int) {
        binding?.apply {
            recyclerCategory.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    lastPosition =
                        (recyclerCategory.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                    totalPosition = adapter.itemCount
                    Log.d("CURRENT", currentPage.toString())
                    if (lastPosition != currentPosition && ((lastPosition == totalPosition - 3 && totalPosition % 2 == 0) || (lastPosition == totalPosition - 2 && totalPosition % 2 != 0))) {
                        currentPage++
                        viewModel.getProductsInCategory(categoryId, 10, currentPage, 100)
                        currentPosition = lastPosition
//                        observeProducts()
                    }
                }
            })
        }
    }
}