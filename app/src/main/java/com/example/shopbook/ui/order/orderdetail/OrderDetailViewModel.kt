package com.example.shopbook.ui.order.orderdetail

import androidx.lifecycle.ViewModel
import com.example.shopbook.data.model.OrderDetail
import com.example.shopbook.data.model.OrderDetailProduct
import com.example.shopbook.data.model.Product

class OrderDetailViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val orderDetailList = mutableListOf<OrderDetailProduct>()

    init {
        orderDetailList.add(OrderDetailProduct(1, 1, "sfsfdsfs", 12, "1222", "212121"))
        orderDetailList.add(OrderDetailProduct(1, 1, "sfsfdsfs", 12, "1222", "212121"))
        orderDetailList.add(OrderDetailProduct(1, 1, "sfsfdsfs", 12, "1222", "212121"))
        orderDetailList.add(OrderDetailProduct(1, 1, "sfsfdsfs", 12, "1222", "212121"))
    }

    fun getOrderDetails(): List<OrderDetailProduct> {
        return orderDetailList
    }
}