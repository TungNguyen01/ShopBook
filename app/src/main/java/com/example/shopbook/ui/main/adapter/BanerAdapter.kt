package com.example.shopbook.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopbook.R
import com.example.shopbook.data.model.Banner
import com.example.shopbook.data.model.CartItemBag
import com.example.shopbook.ui.adapter.OnItemClickListener
import com.example.shopbook.utils.FormatMoney

class BanerAdapter : RecyclerView.Adapter<BanerAdapter.BanerViewHolder>() {
    private var cartList: MutableList<Banner> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BanerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_baner, parent, false)
        return BanerViewHolder(view)
    }


    fun updateData(newData: List<Banner>) {
        cartList.clear()
        cartList.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: BanerViewHolder, position: Int) {
        val book = cartList[position]
        holder.bind(book)

    }
    class BanerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageBaner : ImageView = itemView.findViewById(R.id.img)
        fun bind(banner: Banner){
            Glide.with(itemView.context)
                .load(banner.banner_url)
                .into(imageBaner)
        }
    }
    override fun getItemCount(): Int {
        return cartList.size
    }

}