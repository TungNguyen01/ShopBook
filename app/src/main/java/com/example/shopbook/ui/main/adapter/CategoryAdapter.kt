package com.example.shopbook.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shopbook.R
import com.example.shopbook.data.model.Category
import com.example.shopbook.data.model.HotBook
import com.example.shopbook.ui.adapter.OnItemClickListener


class CategoryAdapter() : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    private var category: MutableList<Category> = mutableListOf()
    private var onItemClickListener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = category[position]
        holder.bind(category)
        holder.categoryTextView.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
    override fun getItemCount(): Int {
        return category.size
    }
    fun getCategory(position: Int): Category = category[position]
    fun updateData(newData: List<Category>) {
        category.clear()
        category.addAll(newData)
        notifyDataSetChanged()
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryTextView: TextView = itemView.findViewById(R.id.tv_category)

        fun bind(category: Category) {
            categoryTextView.text = category.name
        }
    }
}
