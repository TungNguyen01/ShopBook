package com.example.shopbook.ui.main.adapter

import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
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
import com.example.shopbook.ui.adapter.OnItemClickListener
import com.example.shopbook.utils.FormatMoney


class NewArrivalAdapter() : RecyclerView.Adapter<NewArrivalAdapter.NewArrivalViewHolder>() {
    private var newbook: MutableList<NewArrival> = mutableListOf()
    private var onItemClickListener: OnItemClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewArrivalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_new_arrival, parent, false)
        return NewArrivalViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewArrivalViewHolder, position: Int) {
        val newArrival = newbook[position]
        holder.bind(newArrival)
        holder.imgNewBook.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
    fun getBook(position: Int): NewArrival = newbook[position]
    override fun getItemCount(): Int {
        return newbook.size
    }
    fun updateData(newData: List<NewArrival>) {
        newbook.clear()
        newbook.addAll(newData)
        notifyDataSetChanged()
    }
    class NewArrivalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgNewBook : ImageView = itemView.findViewById(R.id.book1)
        private val nameBookTextView: TextView = itemView.findViewById(R.id.tv_namebook)
        private val priceTextView: TextView = itemView.findViewById(R.id.tv_price)
        private val spriceTextView: TextView = itemView.findViewById(R.id.tv_sprice)
        val formatMoney = FormatMoney()
        fun bind(newArrival: NewArrival) {
            nameBookTextView.text = newArrival.name
            priceTextView.text = newArrival.discounted_price.toDouble()?.let { formatMoney.formatMoney(it.toLong()) }.toString()
            spriceTextView.text =setPrice(  newArrival.price.toDouble()?.let { formatMoney.formatMoney(it.toLong()) }.toString())
            Glide.with(itemView.context)
                .load(newArrival.thumbnail)
                .into(imgNewBook)
        }
        fun setPrice(price: String): SpannableString {
            val content = SpannableString(price)
            content.setSpan(
                StrikethroughSpan(),
                0,
                price.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            content.setSpan(
                RelativeSizeSpan(12 / 14f),
                0,
                price.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            return content
        }
    }
}

