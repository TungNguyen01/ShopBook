package com.example.shopbook.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopbook.data.model.OrderDetailProduct
import com.example.shopbook.databinding.ItemOrderDetailBinding

class OrderDetailAdapter(private var orderDetailProductList: List<OrderDetailProduct>) :
    RecyclerView.Adapter<OrderDetailAdapter.ViewHolder>() {
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
        fun bind(orderDetailProduct: OrderDetailProduct) {
//            Glide.with(binding.root)
//                .load(orderDetailProduct.image)
//                .centerCrop()
////                .placeholder(R.drawable.placeholder_image)
////                .error(R.drawable.error_image)
//                .into(binding.imageProduct)
            binding.textPrice.text = orderDetailProduct.subtotal
            binding.textName.text = orderDetailProduct.productName
            binding.textNumber.text = orderDetailProduct.quantity.toString()
        }
    }
}