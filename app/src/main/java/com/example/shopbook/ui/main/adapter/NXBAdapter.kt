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
import com.example.shopbook.data.model.Supply
import com.example.shopbook.ui.adapter.OnItemClickListener
import com.example.shopbook.utils.FormatMoney

class NXBAdapter: RecyclerView.Adapter<NXBAdapter.NXBViewHolder>() {
    private var supply: MutableList<Supply> = mutableListOf()
    private var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NXBViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return NXBViewHolder(view)
    }

    override fun onBindViewHolder(holder: NXBViewHolder, position: Int) {
        val supply = supply[position]
        holder.bind(supply)
        holder.imgBook.setOnClickListener {
            onItemClickListener?.onItemClick(position)
        }
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
    fun getNXB(position: Int): Supply = supply[position]
    override fun getItemCount(): Int {
        return supply.size
    }
    fun updateData(newData: List<Supply>) {
        supply.clear()
        supply.addAll(newData)
        notifyDataSetChanged()
    }
    class NXBViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgBook : ImageView = itemView.findViewById(R.id.image_product)
        private val textPrice : TextView = itemView.findViewById(R.id.text_price)
        private val textName : TextView = itemView.findViewById(R.id.text_name)
        val imgCart : ImageView = itemView.findViewById(R.id.image_cart)
        private val textSprice : TextView = itemView.findViewById(R.id.text_discount_price)
        val cardView : CardView = itemView.findViewById(R.id.cardview)
        val formatMoney = FormatMoney()
        fun bind(supply: Supply) {
            Glide.with(itemView.context)
                .load(supply.thumbnail)
                .into(imgBook)
            textName.text = supply.name
            textPrice.text = supply.price.toDouble()?.let { formatMoney.formatMoney(it.toLong()) }.toString()
            textSprice.text = supply.discounted_price.toDouble()?.let { formatMoney.formatMoney(it.toLong()) }.toString()
            cardView.setOnClickListener{
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){

                }
            }
        }
    }
}