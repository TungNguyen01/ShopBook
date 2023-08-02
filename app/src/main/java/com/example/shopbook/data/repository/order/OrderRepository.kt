package com.example.shopbook.data.repository.order

import com.example.shopbook.data.model.OrderDetail
import com.example.shopbook.data.model.OrderList
import retrofit2.Response


interface OrderRepository {
    suspend fun getOrderHistory(): Response<OrderList>?
    suspend fun getOrderDetail(orderId: Int): Response<OrderDetail>?
}