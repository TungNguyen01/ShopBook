package com.example.shopbook.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopbook.R
import com.example.shopbook.data.model.Wishlist
import com.example.shopbook.ui.adapter.OnItemClickListener
import com.example.shopbook.utils.FormatMoney

class WishListAdapter : RecyclerView.Adapter<WishListAdapter.WishListViewHolder>() {
    private var wishList: MutableList<Wishlist> = mutableListOf()
    private var onItemClickListener: OnItemClickListener? = null
    private var onItemClickListener2: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wishlist, parent, false)
        return WishListViewHolder(view)
    }

    fun updateData(newData: List<Wishlist>) {
        wishList.clear()
        wishList.addAll(newData)
        notifyDataSetChanged()
    }
    fun getPrice(position: Int) : String{
        return wishList[position].discount
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
    fun setOnItemClickListener2(listener: OnItemClickListener) {
        onItemClickListener2 = listener
    }
    override fun onBindViewHolder(holder: WishListViewHolder, position: Int) {
        val book = wishList[position]
        holder.bind(book)
        holder.imgAdd.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }
    class WishListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgWishList : ImageView = itemView.findViewById(R.id.image_bookbag)
        val textName : TextView = itemView.findViewById(R.id.textview_name)
        val textPrice : TextView = itemView.findViewById(R.id.textview_price)
        val imgAdd : ImageView = itemView.findViewById(R.id.image_add)
        val formatMoney = FormatMoney()
        fun bind(wishlist: Wishlist){
            Glide.with(itemView.context)
                .load(wishlist.thumbnail)
                .into(imgWishList)
            textName.text = wishlist.name
            textPrice.text = wishlist.price.toDouble()?.let { formatMoney.formatMoney(it.toLong()) }.toString()
        }
    }
    override fun getItemCount(): Int {
        return wishList.size
    }

}
