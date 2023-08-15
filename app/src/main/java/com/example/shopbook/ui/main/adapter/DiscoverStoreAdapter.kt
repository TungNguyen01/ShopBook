package com.example.shopbook.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.shopbook.R
import com.example.shopbook.data.model.Author
import com.example.shopbook.data.model.HotBook
import com.example.shopbook.data.model.NewArrival
import com.example.shopbook.ui.adapter.OnItemClickListener

class DiscoverStoreAdapter() : RecyclerView.Adapter<DiscoverStoreAdapter.DiscoverStoreViewHolder>() {
    private var author: MutableList<Author> = mutableListOf()
    private var onItemClickListener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverStoreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_discover_store, parent, false)
        return DiscoverStoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiscoverStoreViewHolder, position: Int) {
        val discoverStore = author[position]
        holder.bind(discoverStore)
        holder.imgAuthor.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }

    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
    fun getAuthor(position: Int): Author = author[position]
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
        val imgAuthor : ImageView = itemView.findViewById(R.id.book1)
        private val cardView : CardView = itemView.findViewById(R.id.cardview_book)
        fun bind(discoverStore: Author) {
            nameStoreTextView.text = discoverStore.authorName
            Glide.with(itemView.context)
                .load(discoverStore.authorAvatar)
                .transform(RoundedCorners(500))
                .into(imgAuthor)
            cardView.setOnClickListener{
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){

                }
            }
        }
    }
}
