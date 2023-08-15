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
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopbook.R
import com.example.shopbook.data.model.HotBook
import com.example.shopbook.data.model.Product
import com.example.shopbook.ui.adapter.OnItemClickListener
import com.example.shopbook.utils.FormatMoney

class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    private var hotBooks: MutableList<HotBook> = mutableListOf()
    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hot_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val hotBook = hotBooks[position]
        holder.bind(hotBook)
        holder.imgBook.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
    fun getBook(position: Int): HotBook = hotBooks[position]
    override fun getItemCount(): Int {
        return hotBooks.size
    }
    fun updateData(newData: List<HotBook>) {
        hotBooks.clear()
        hotBooks.addAll(newData)
        notifyDataSetChanged()
    }
    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgBook : ImageView = itemView.findViewById(R.id.book1)
        private val bookName: TextView = itemView.findViewById(R.id.tv_namebook)
        private val bookPrice: TextView = itemView.findViewById(R.id.tv_price)
        private val bookSPrice: TextView = itemView.findViewById(R.id.tv_sprice)
        private val cardView : CardView = itemView.findViewById(R.id.img_book)
        val formatMoney = FormatMoney()
        fun bind(book: HotBook) {
            bookName.text = book.name
            bookPrice.text = book.discounted_price.toDouble()?.let { formatMoney.formatMoney(it.toLong()) }.toString()
            bookSPrice.text =setPrice( book.price.toDouble()?.let { formatMoney.formatMoney(it.toLong()) }.toString())
            Glide.with(itemView.context)
                .load(book.thumbnail)
                .into(imgBook)

            cardView.setOnClickListener{
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){

                }
            }
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