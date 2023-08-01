package com.example.shopbook.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopbook.R
import com.example.shopbook.data.model.HotBook
import com.example.shopbook.data.model.NewArrival


class NewArrivalAdapter() : RecyclerView.Adapter<NewArrivalAdapter.NewArrivalViewHolder>() {
    private var newbook: MutableList<NewArrival> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewArrivalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_new_arrival, parent, false)
        return NewArrivalViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewArrivalViewHolder, position: Int) {
        val newArrival = newbook[position]
        holder.bind(newArrival)
    }

    override fun getItemCount(): Int {
        return newbook.size
    }
    fun updateData(newData: List<NewArrival>) {
        newbook.clear()
        newbook.addAll(newData)
        notifyDataSetChanged()
    }
    class NewArrivalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgNewBook : ImageView = itemView.findViewById(R.id.book1)
        private val nameBookTextView: TextView = itemView.findViewById(R.id.tv_namebook)
        private val priceTextView: TextView = itemView.findViewById(R.id.tv_price)
        private val spriceTextView: TextView = itemView.findViewById(R.id.tv_sprice)

        fun bind(newArrival: NewArrival) {
            nameBookTextView.text = newArrival.name
            priceTextView.text = newArrival.price
            spriceTextView.text = newArrival.discounted_price
            Glide.with(itemView.context)
                .load(newArrival.thumbnail)
                .into(imgNewBook)
        }
    }
}

