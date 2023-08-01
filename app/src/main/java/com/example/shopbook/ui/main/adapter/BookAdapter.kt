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
import com.example.shopbook.data.model.Product

class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    private var hotBooks: MutableList<HotBook> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hot_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val hotBook = hotBooks[position]
        holder.bind(hotBook)
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

        fun bind(book: HotBook) {
//            Glide.with(binding.root)
//                .load(product.image)
//                .centerCrop()
////                .placeholder(R.drawable.placeholder_image)
////                .error(R.drawable.error_image)
//                .into(binding.imageProduct)
            bookName.text = book.name
            bookPrice.text = book.price
            bookSPrice.text = book.discounted_price
//            Glide.with(itemView)
//                .load(boo) // Assuming book.image is the image URL or resource ID
//                .into(imgBook)
//fun bind(product: Product) {
//    Glide.with(binding.root)
//        .load(product.image)
//        .centerCrop()
////                .placeholder(R.drawable.placeholder_image)
////                .error(R.drawable.error_image)
//        .into(binding.imageProduct)
            Glide.with(itemView.context)
                .load(book.thumbnail)
                .into(imgBook)
        }
    }
}