package com.example.shopbook.ui.adapter

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopbook.data.model.Book
import com.example.shopbook.data.model.Product
import com.example.shopbook.databinding.ItemProductBinding
import com.example.shopbook.utils.FormatMoney

class BookAdapter :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {
    private var productList: MutableList<Product> = mutableListOf()
    private var onItemClickListener: OnItemClickListener? = null
    private var addItemToCart: OnItemClickListener? = null
    private val formatMoney = FormatMoney()

    @SuppressLint("NotifyDataSetChanged")
    fun addData(products: List<Product>) {
        productList.addAll(products)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearData() {
        productList.clear()
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(products: List<Product>) {
        productList = products as MutableList<Product>
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    fun setAddItemToCart(listener: OnItemClickListener) {
        addItemToCart = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    fun getBook(position: Int): Product = productList[position]
    override fun getItemCount(): Int = productList.size

    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            Glide.with(binding.root)
                .load(product.thumbnail)
                .centerCrop()
                .into(binding.imageProduct)
            if (product.discounted_price != null) {
                val layoutParams =
                    binding.textPrice.layoutParams as ViewGroup.MarginLayoutParams
                val newMarginTopInDp = 0
//                val newMarginTopInPx = TypedValue.applyDimension(
//                    TypedValue.COMPLEX_UNIT_DIP, newMarginTopInDp.toFloat(),
//                    resources.displayMetrics
//                ).toInt()
                binding.textDiscountPrice.visibility = View.VISIBLE
                layoutParams.topMargin = newMarginTopInDp
                binding.textPrice.layoutParams = layoutParams
                binding.textDiscountPrice.text =
                    product.discounted_price?.toDouble()
                        ?.let { formatMoney.formatMoney(it.toLong()) }
                binding.textPrice.text = setPrice(
                    product.price?.toDouble()
                        ?.let { formatMoney.formatMoney(it.toLong()) }.toString()
                )
            } else {
                binding.textPrice.text = product.price?.toDouble()
                    ?.let { formatMoney.formatMoney(it.toLong()) }
            }

            binding.textName.text = product.name
            binding.cardview.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemClick(position)
                }
            }
            binding.imageCart.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemClick(position)
                }
            }
            binding.imageCart.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    addItemToCart?.onItemClick(position)
                }
            }
        }
    }

    private fun setPrice(price: String): SpannableString {
        val content = SpannableString(price)
        content.setSpan(
            StrikethroughSpan(),
            0,
            price.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        content.setSpan(
            RelativeSizeSpan(12 / 14f),
            0,
            price.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return content
    }
}