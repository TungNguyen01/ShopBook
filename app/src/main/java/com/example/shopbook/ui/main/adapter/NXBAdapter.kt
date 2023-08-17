package com.example.shopbook.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopbook.data.model.Product
import com.example.shopbook.databinding.ItemProductBinding
import com.example.shopbook.databinding.ItemSupllyTypeBinding
import com.example.shopbook.ui.adapter.OnItemClickListener
import com.example.shopbook.utils.FormatMoney

class NXBAdapter(private val isGridMode : Boolean) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var productList: MutableList<Product> = mutableListOf()
    private var onItemClickListener: OnItemClickListener? = null
    private var addItemToCart: OnItemClickListener? = null
    private val formatMoney = FormatMoney()
    companion object {
        private const val VIEW_TYPE_GRID = 0
        private const val VIEW_TYPE_LIST = 1
    }

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
        productList.clear()
        productList.addAll(products)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    fun setAddItemToCart(listener: OnItemClickListener) {
        addItemToCart = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_GRID) {
            val binding = ItemProductBinding.inflate(inflater, parent, false)
            ViewHolder(binding)
        } else {
            val binding = ItemSupllyTypeBinding.inflate(inflater, parent, false)
            ViewHolderNew(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val product = productList[position]
        when (holder) {
            is ViewHolder -> holder.bind(product, isGridMode)
            is ViewHolderNew -> holder.bind(product, isGridMode)
        }
    }

    fun getBook(position: Int): Product = productList[position]

    override fun getItemCount(): Int = productList.size

    override fun getItemViewType(position: Int): Int {
        return if (isGridMode) VIEW_TYPE_GRID else VIEW_TYPE_LIST
    }

    inner class ViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product, isGridMode: Boolean) {
            Glide.with(binding.root)
                .load(product.thumbnail)
                .centerCrop()
                .into(binding.imageProduct)
            if (product.discounted_price != null) {
                val layoutParams = binding.textPrice.layoutParams as ViewGroup.MarginLayoutParams
                val newMarginTopInDp = 0
                binding.textDiscountPrice.visibility = android.view.View.VISIBLE
                layoutParams.topMargin = newMarginTopInDp
                binding.textPrice.layoutParams = layoutParams
                binding.textDiscountPrice.text = product.discounted_price?.toDouble()?.let {
                    formatMoney.formatMoney(it.toLong())
                }
                binding.textPrice.text = setPrice(
                    product.price?.toDouble()?.let {
                        formatMoney.formatMoney(it.toLong())
                    }.toString()
                )
            } else {
                binding.textPrice.text = product.price?.toDouble()?.let {
                    formatMoney.formatMoney(it.toLong())
                }
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

    inner class ViewHolderNew(private val binding: ItemSupllyTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product, isGridMode: Boolean) {
            Glide.with(binding.root)
                .load(product.thumbnail)
                .centerCrop()
                .into(binding.imageProduct)
            if (product.discounted_price != null) {
                binding.textPrice.text = setPrice(
                    product.price?.toDouble()?.let {
                        formatMoney.formatMoney(it.toLong())
                    }.toString()
                )
            } else {
                binding.textPrice.text = product.price?.toDouble()?.let {
                    formatMoney.formatMoney(it.toLong())
                }
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

    private fun setPrice(price: String): android.text.SpannableString {
        val content = android.text.SpannableString(price)
        content.setSpan(
            android.text.style.StrikethroughSpan(),
            0,
            price.length,
            android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        content.setSpan(
            android.text.style.RelativeSizeSpan(12 / 14f),
            0,
            price.length,
            android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return content
    }
}
