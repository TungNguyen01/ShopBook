package com.example.shopbook.ui.productdetail

import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.shopbook.R
import com.example.shopbook.data.model.ProductInfoList
import com.example.shopbook.databinding.FragmentProductDetailBinding
import com.example.shopbook.ui.author.AuthorFragment
import com.example.shopbook.ui.profile.ProfileFragment
import com.example.shopbook.utils.FormatMoney
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProductdetailFragment : Fragment() {
    private var binding: FragmentProductDetailBinding? = null
    private lateinit var viewModel: ProductdetailViewModel
    private val formatMoney= FormatMoney()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProductDetailBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductdetailViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.visibility = View.GONE
        binding?.loadingLayout?.root?.visibility = View.VISIBLE
        val productId = arguments?.getString("bookId")?.toInt()
        var authorId = 0
        productId?.let {
            viewModel.getProductInfo(it)
            viewModel.productInfo.observe(viewLifecycleOwner, Observer { productInfoList ->
                if (productInfoList != null) {
                    bindData(productInfoList)
                    authorId = productInfoList.author.authorId
                } else {
                    Log.d("NULLLL", "HEllo")
                }
            })
        }

        readmoreInfo()

        binding?.apply {
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            imageAccount.setOnClickListener {
                val profileFragment = ProfileFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, profileFragment)
                    .addToBackStack("productFragment")
                    .commit()
            }
            textNameAuthor.setOnClickListener {
                val authorFragment = AuthorFragment()
                val bundle = Bundle()
                bundle.putString("authorId", authorId.toString())
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, authorFragment.apply { arguments = bundle })
                    .addToBackStack("productFragment")
                    .commit()
            }
            textAdditemtocart.setOnClickListener {
                if (productId != null) {
                    viewModel.addItemToCart(productId)
                    Toast.makeText(context, "ADD ITEM TO CART SUCCESSFUL", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindData(productInfoList: ProductInfoList) {
        binding?.apply {
            Glide.with(root)
                .load(productInfoList.product.thumbnail)
                .centerCrop()
                .into(imagePro)
            textName.text = productInfoList.product.name
            textNum.text = resources.getString(R.string.quantity)
            textMa.text =
                resources.getString(R.string.productId) + " " + productInfoList.product.productId
            textDescription.text = productInfoList.product.description
            textPrice.text =
                formatMoney.formatMoney(productInfoList.product.price.toDouble().toLong())
            textAuthor.text = resources.getString(R.string.author) + ": "
            textNameAuthor.text = setAuthorName(productInfoList.author.authorName)
            textNcc.text =
                resources.getString(R.string.supplier) + ": " + productInfoList.supplier.supplier_name
            textYear.text = resources.getString(R.string.year)
            textLanguage.text = resources.getString(R.string.language)
            readmore.text = resources.getString(R.string.readmore)
            textPublish.text = productInfoList.supplier.supplier_name
            textPriceName.text = resources.getString(R.string.price)
            if (productInfoList.product.wishlist == 1) {
                imageFavorite.setBackgroundResource(R.drawable.bg_ellipse_favor)
                imageFavorite.setImageResource(R.drawable.ic_favorite)
            }
            binding?.loadingLayout?.root?.visibility = View.INVISIBLE
        }
    }

    private fun readmoreInfo() {
        var check = true
        binding?.apply {
            val layoutParams = constraintImageProduct.layoutParams as ConstraintLayout.LayoutParams
            readmore.setOnClickListener {
                if (check) {
                    layoutParams.dimensionRatio = "12:7"
                    constraintImageProduct.layoutParams = layoutParams
                    val newMaxLines = Integer.MAX_VALUE
                    textDescription.maxLines = newMaxLines
                    check = false
                    readmore.text = "Collapse."
                } else {
                    layoutParams.dimensionRatio = "6:4"
                    constraintImageProduct.layoutParams = layoutParams
                    val newMaxLines = 2
                    textDescription.maxLines = newMaxLines
                    readmore.text = "Read more."
                    check = true
                }
            }
        }
    }

    private fun setAuthorName(name: String): SpannableString {
        val content = SpannableString(name)
        content.setSpan(UnderlineSpan(), 0, name.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val underlineColor = resources.getColor(R.color.colorAuth)
        content.setSpan(
            ForegroundColorSpan(underlineColor),
            0,
            name.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return content
    }
}