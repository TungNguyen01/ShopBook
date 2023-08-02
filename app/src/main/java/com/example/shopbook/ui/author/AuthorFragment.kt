package com.example.shopbook.ui.author

import android.graphics.Typeface
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopbook.R
import com.example.shopbook.data.model.Product
import com.example.shopbook.ui.adapter.BookAdapter
import com.example.shopbook.databinding.FragmentAuthorBinding
import com.example.shopbook.ui.adapter.ItemSpacingDecoration
import com.example.shopbook.ui.adapter.OnItemClickListener
import com.example.shopbook.ui.productdetail.ProductdetailFragment

class AuthorFragment : Fragment() {

    companion object {
        fun newInstance() = AuthorFragment()
    }

    private lateinit var viewModel: AuthorViewModel
    private var binding: FragmentAuthorBinding? = null
    private lateinit var adapter: BookAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAuthorBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AuthorViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.loadingLayout?.root?.visibility = View.VISIBLE
        val authorId = arguments?.getString("authorId")?.toInt()
        authorId?.let {
            viewModel.getProductsByAuthor(authorId)
            viewModel.getAuthor(authorId)
            observeProducts()
        }
        val horizontalSpacing =
            resources.getDimensionPixelSize(R.dimen.horizontal_spacing)
        val verticalSpacing =
            resources.getDimensionPixelSize(R.dimen.vertical_spacing)
        binding?.apply {
            searchProduct.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    // task HERE
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText.isEmpty()) {
                        textAuthor.visibility = View.VISIBLE
                        if (authorId != null) {
                            viewModel.getProductsByAuthor(authorId)
                        }
                        observeProducts()
                    } else {
                        val layoutParams =
                            searchProduct.layoutParams as ViewGroup.MarginLayoutParams
                        val newMarginTopInDp = 12
                        val newMarginTopInPx = TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, newMarginTopInDp.toFloat(),
                            resources.displayMetrics
                        ).toInt()
                        layoutParams.topMargin = newMarginTopInPx
                        searchProduct.layoutParams = layoutParams
                        textAuthor.visibility = View.GONE;
                        authorId?.let {
                            viewModel.getSearchAuthorProduct(it, newText)
                            observeProducts()
                        }
                    }
                    return false;
                }
            })
            recyclerAuthor.addItemDecoration(
                ItemSpacingDecoration(
                    horizontalSpacing,
                    verticalSpacing
                )
            )
            recyclerAuthor.layoutManager = GridLayoutManager(context, 2)
//            recyclerAuthor.adapter = adapter
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }

    }

    private fun observeProducts() {
        viewModel.productList.observe(viewLifecycleOwner, Observer { productList ->
            if (productList != null) {
                adapter = BookAdapter(productList)
                binding?.recyclerAuthor?.adapter = adapter
                navToProductDetail()
                binding?.loadingLayout?.root?.visibility = View.INVISIBLE
            } else {
                Log.d("NULLLL", "HEllo")
            }
        })
        viewModel.author.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                if (!it.author.authorDescription.contains(it.author.authorName))
                    it.author.authorDescription =
                        it.author.authorName + " " + it.author.authorDescription
                binding?.textAuthor?.text =
                    setAuthorName(it.author.authorDescription, it.author.authorName)
            }
        })
        viewModel.productList.observe(viewLifecycleOwner, Observer { productList ->
            adapter = BookAdapter(productList)
            binding?.recyclerAuthor?.adapter = adapter
            navToProductDetail()
        })
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
                    .addToBackStack("AuthorFragment")
                    .commit()
            }
        })
    }

    private fun setAuthorName(authorDes: String, authorName: String): SpannableString {
        val content = SpannableString(authorDes)
        content.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            authorName.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        content.setSpan(
            RelativeSizeSpan(1.25f),
            0,
            authorName.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return content
    }
}