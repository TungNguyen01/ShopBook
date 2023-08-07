package com.example.shopbook.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopbook.R
import com.example.shopbook.data.model.CartItemBag
import com.example.shopbook.ui.adapter.OnItemClickListener


class BagAdapter : RecyclerView.Adapter<BagAdapter.BagViewHolder>() {
    private var cartList: MutableList<CartItemBag> = mutableListOf()
    private var onItemClickListener: OnItemClickListener? = null
    private var onItemClickListener2: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return BagViewHolder(view)
    }

    fun updateData(newData: List<CartItemBag>) {
        cartList.clear()
        cartList.addAll(newData)
        notifyDataSetChanged()
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
    fun setOnItemClickListener2(listener: OnItemClickListener) {
        onItemClickListener2 = listener
    }
    override fun onBindViewHolder(holder: BagViewHolder, position: Int) {
        val book = cartList[position]
        holder.bind(book)
        holder.imgAdd.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
        holder.imgReduce.setOnClickListener {
            onItemClickListener2?.onItemClick(position)
        }
    }
    class BagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageBookBag: ImageView = itemView.findViewById(R.id.image_bookbag)
        val textName: TextView = itemView.findViewById(R.id.textview_name)
        val textPrice: TextView = itemView.findViewById(R.id.textview_price)
        val textQuantity : TextView = itemView.findViewById(R.id.textview_quantity)
        val imgAdd :ImageView = itemView.findViewById(R.id.image_add)
        val imgReduce : ImageView = itemView.findViewById(R.id.image_reduce)
        fun bind(cart: CartItemBag){
            Glide.with(itemView.context)
            .load(cart.image)
            .into(imageBookBag)
            textName.text = cart.name
            textPrice.text = cart.price
            textQuantity.text = cart.quantity.toString()
        }
    }
    override fun getItemCount(): Int {
        return cartList.size
    }

}


