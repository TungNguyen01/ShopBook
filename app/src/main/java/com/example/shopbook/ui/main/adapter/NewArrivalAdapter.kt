package com.example.shopbook.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shopbook.R
import com.example.shopbook.data.model.NewArrival


class NewArrivalAdapter(private val newArrivals: List<NewArrival>) : RecyclerView.Adapter<NewArrivalAdapter.NewArrivalViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewArrivalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_new_arrival, parent, false)
        return NewArrivalViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewArrivalViewHolder, position: Int) {
        val newArrival = newArrivals[position]
        holder.bind(newArrival)
    }

    override fun getItemCount(): Int {
        return newArrivals.size
    }

    class NewArrivalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameBookTextView: TextView = itemView.findViewById(R.id.tv_namebook)
        private val priceTextView: TextView = itemView.findViewById(R.id.tv_price)
        private val spriceTextView: TextView = itemView.findViewById(R.id.tv_sprice)

        fun bind(newArrival: NewArrival) {
            nameBookTextView.text = newArrival.nameBook
            priceTextView.text = newArrival.price
            spriceTextView.text = newArrival.sprice
        }
    }
}
