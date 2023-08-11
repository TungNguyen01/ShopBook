package com.example.shopbook.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shopbook.data.model.Category
import com.example.shopbook.data.model.Product
import com.example.shopbook.databinding.ItemCategoryIndexBinding

class CategoryIndexAdapter() :
    RecyclerView.Adapter<CategoryIndexAdapter.ViewHolder>() {
    private lateinit var onItemClickListener: OnItemClickListener
    private var categoryList: MutableList<Category> = mutableListOf()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CategoryIndexAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryIndexBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(categories: List<Category>) {
        categoryList.clear()
        categoryList.addAll(categories)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CategoryIndexAdapter.ViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    fun getCategory(position: Int): Category {
        return categoryList[position]
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class ViewHolder(private val binding: ItemCategoryIndexBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.nameCategory.text = category.name
            binding.cardviewCategory.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(position)
                }
            }
        }
    }
}