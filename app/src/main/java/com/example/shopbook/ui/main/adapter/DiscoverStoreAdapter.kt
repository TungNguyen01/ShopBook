package com.example.shopbook.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shopbook.R
import com.example.shopbook.data.model.DiscoverStore

class DiscoverStoreAdapter(private val discoverStores: List<DiscoverStore>) : RecyclerView.Adapter<DiscoverStoreAdapter.DiscoverStoreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverStoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_discover_store, parent, false)
        return DiscoverStoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiscoverStoreViewHolder, position: Int) {
        val discoverStore = discoverStores[position]
        holder.bind(discoverStore)
    }

    override fun getItemCount(): Int {
        return discoverStores.size
    }

    class DiscoverStoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameStoreTextView: TextView = itemView.findViewById(R.id.tv_nameStore)

        fun bind(discoverStore: DiscoverStore) {
            nameStoreTextView.text = discoverStore.nameStore
        }
    }
}
