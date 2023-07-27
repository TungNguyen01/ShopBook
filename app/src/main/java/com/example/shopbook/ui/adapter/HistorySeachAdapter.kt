package com.example.shopbook.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopbook.data.model.Product
import com.example.shopbook.databinding.ItemHistorySearchBinding

class HistorySeachAdapter(private val historyList: List<Product>) :
    RecyclerView.Adapter<HistorySeachAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistorySearchBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val history = historyList[position]
        holder.bind(history)
    }

    override fun getItemCount(): Int {
        return 5
    }

    inner class ViewHolder(private val binding: ItemHistorySearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.textItemHistroy.text=product.name
            binding.textItemHistroy.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemClick(position)
                }
            }
        }
    }
}