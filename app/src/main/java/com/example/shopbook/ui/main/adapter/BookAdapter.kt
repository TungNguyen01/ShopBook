package com.example.shopbook.ui.main.adapter

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
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return hotBooks.size
    }
    fun updateData(newData: List<HotBook>) {
        hotBooks.clear()
        hotBooks.addAll(newData)
        notifyDataSetChanged()
    }
    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgBook : ImageView = itemView.findViewById(R.id.book1)
        private val bookName: TextView = itemView.findViewById(R.id.tv_namebook)
        private val bookPrice: TextView = itemView.findViewById(R.id.tv_price)
        private val bookSPrice: TextView = itemView.findViewById(R.id.tv_sprice)
        private val cardView : CardView = itemView.findViewById(R.id.img_book)

        fun bind(book: HotBook) {

            bookName.text = book.name
            bookPrice.text = book.price
            bookSPrice.text = book.discounted_price
            Glide.with(itemView.context)
                .load(book.thumbnail)
                .into(imgBook)
//            binding.cardview.setOnClickListener {
//                val position = adapterPosition
//                if (position != RecyclerView.NO_POSITION) {
//                    onItemClickListener?.onItemClick(position)
//                }
//            }
            cardView.setOnClickListener{
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){

                }
            }
        }
    }
}