package com.example.shopbook.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopbook.R
import com.example.shopbook.data.model.Author

class DiscoverStoreAdapter() : RecyclerView.Adapter<DiscoverStoreAdapter.DiscoverStoreViewHolder>() {
    private var author: MutableList<Author> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverStoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_discover_store, parent, false)
        return DiscoverStoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiscoverStoreViewHolder, position: Int) {
        val discoverStore = author[position]
        holder.bind(discoverStore)
    }

    override fun getItemCount(): Int {
        return author.size
    }
    fun updateData(newData: List<Author>) {
        author.clear()
        author.addAll(newData)
        notifyDataSetChanged()
    }
    class DiscoverStoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameStoreTextView: TextView = itemView.findViewById(R.id.tv_nameStore)
        private val imgAuthor : ImageView = itemView.findViewById(R.id.book1)

        fun bind(discoverStore: Author) {
            nameStoreTextView.text = discoverStore.author_name
            Glide.with(itemView.context)
                .load(discoverStore.author_avatar)
                .into(imgAuthor)
        }
    }
}
