package com.example.shopbook.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopbook.R
import com.example.shopbook.data.model.OrderDetailProduct
import com.example.shopbook.databinding.ItemOrderDetailBinding
import com.example.shopbook.utils.FormatMoney

class OrderDetailAdapter(private var orderDetailProductList: List<OrderDetailProduct>) :
    RecyclerView.Adapter<OrderDetailAdapter.ViewHolder>() {
    private val formatMoney=FormatMoney()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): OrderDetailAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemOrderDetailBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderDetailAdapter.ViewHolder, position: Int) {
        val orderDetailProductList = orderDetailProductList[position]
        holder.bind(orderDetailProductList)
    }

    override fun getItemCount(): Int = orderDetailProductList.size

    inner class ViewHolder(private val binding: ItemOrderDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(orderDetailProduct: OrderDetailProduct) {
            Glide.with(binding.root)
                .load(orderDetailProduct.image)
                .centerCrop()
                .into(binding.imageProduct)
            binding.textPrice.text = orderDetailProduct.subtotal?.let { formatMoney.formatMoney(it.toDouble().toLong()) }
            binding.textName.text = orderDetailProduct.productName
            binding.textNumber.text = "x"+orderDetailProduct.quantity.toString()
            if(orderDetailProduct.wishlist==0){
                binding.imageFavorite.setBackgroundResource(R.drawable.bg_ellipse)
                binding.imageFavorite.setImageResource(R.drawable.favor_white)
            }
        }
    }
}