package com.example.shopbook.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopbook.data.model.Order
import com.example.shopbook.data.model.OrderHistory
import com.example.shopbook.databinding.ItemHeaderOrderHistoryBinding
import com.example.shopbook.databinding.ItemOrderHistoryBinding
import com.example.shopbook.utils.FormatMoney

class OrderHistoryAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //    private val HEADER_VIEW_TYPE = 0
//    private val ITEM_VIEW_TYPE = 1
    private var orderHistoryList: MutableList<OrderHistory> = mutableListOf()
    private val formatMoney = FormatMoney()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == 0) {
            val binding = ItemHeaderOrderHistoryBinding.inflate(inflater, parent, false)
            HeaderViewHolder(binding)
        } else {
            val binding = ItemOrderHistoryBinding.inflate(inflater, parent, false)
            ItemViewHolder(binding)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(orderHistories: List<OrderHistory>) {
        orderHistoryList = orderHistories as MutableList<OrderHistory>
        notifyDataSetChanged()
    }

    private var onItemClickListener: OnItemClickListener? = null
    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return orderHistoryList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (orderHistoryList[position].header != null) 0 else 1
    }

    fun getOrder(position: Int): Order? {
        return orderHistoryList[position].order
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val header = orderHistoryList[position].header
        val itemOrder = orderHistoryList[position].order
        when (holder) {
            is HeaderViewHolder -> header?.let { holder.bind(it) }
            is ItemViewHolder -> itemOrder?.let { holder.bind(it) }
        }
    }

    inner class HeaderViewHolder(private val binding: ItemHeaderOrderHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(header: String) {
            binding.textHeader.text = header
        }
    }

    inner class ItemViewHolder(private val binding: ItemOrderHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Order) {
            binding.textIdOrder.text = "#Order" + item.orderId.toString()
            binding.textNumberPro.text = item.totalQuantity + " sản phẩm"
            binding.textPrice.text = item.orderTotal?.toDouble()
                ?.let { formatMoney.formatMoney(it.toLong()) }
            binding.textStatus.text = item.orderStatus
            binding.iamgeNavRight.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemClick(position)
                }
            }
        }
    }

}
