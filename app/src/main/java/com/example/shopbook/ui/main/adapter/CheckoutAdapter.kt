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
import com.example.shopbook.ui.order.checkout.CheckOutViewModel
import com.example.shopbook.utils.FormatMoney

class CheckoutAdapter : RecyclerView.Adapter<CheckoutAdapter.CheckOutViewHolder>() {
    private var cartList: MutableList<CartItemBag> = mutableListOf()
    private var onItemClickListener: OnItemClickListener? = null
    private var onItemClickListener2: OnItemClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckOutViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_checkout, parent, false)
        return CheckOutViewHolder(view)
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
    override fun onBindViewHolder(holder: CheckOutViewHolder, position: Int) {
        val book = cartList[position]
        holder.bind(book)

    }
    class CheckOutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageBook: ImageView = itemView.findViewById(R.id.image_itemcheckout)
        val textName: TextView = itemView.findViewById(R.id.textview_name)
        val textPrice: TextView = itemView.findViewById(R.id.textview_price)
        val textQuantity : TextView = itemView.findViewById(R.id.textview_quantity)
        val formatMoney = FormatMoney()
        fun bind(cart: CartItemBag){
            Glide.with(itemView.context)
                .load(cart.image)
                .into(imageBook)
            textName.text = cart.name
            textPrice.text = ((cart.price.toDouble())*(cart.quantity.toDouble()))?.let { formatMoney.formatMoney(it.toLong()) }.toString()
            textQuantity.text = cart.quantity.toString()
        }
    }
    override fun getItemCount(): Int {
        return cartList.size
    }
}