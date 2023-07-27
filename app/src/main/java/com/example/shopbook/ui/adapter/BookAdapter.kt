package com.example.shopbook.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopbook.data.model.Product
import com.example.shopbook.databinding.ItemProductBinding

class BookAdapter(private var productList: List<Product>) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    fun setData(products: List<Product>) {
        productList = products
        notifyDataSetChanged()
    }
    private var onItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
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

    override fun getItemCount(): Int = productList.size

    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            Glide.with(binding.root)
                .load(product.image)
                .centerCrop()
//                .placeholder(R.drawable.placeholder_image)
//                .error(R.drawable.error_image)
                .into(binding.imageProduct)
            binding.textPrice.text = product.price
            binding.textName.text = product.name
            binding.cardview.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemClick(position)
                }
            }
        }
    }
}