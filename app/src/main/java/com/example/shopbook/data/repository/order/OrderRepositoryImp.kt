package com.example.shopbook.data.repository.order

import com.example.shopbook.data.model.Messeage
import com.example.shopbook.data.model.OrderDetail
import com.example.shopbook.data.model.OrderList
import com.example.shopbook.datasource.IDataSource
import retrofit2.Response

class OrderRepositoryImp(private val dataSource: IDataSource) : OrderRepository {
    override suspend fun getOrderHistory(): Response<OrderList>? {
        return dataSource.getOrderHistory()
    }

    override suspend fun getOrderDetail(orderId: Int): Response<OrderDetail>? {
        return dataSource.getOrderDetail(orderId)
    }

    override suspend fun createOrder(
        cart_id: String,
        shipping_id: Int,
        address: String,
        receiver_name: String,
        receiver_phone: String
    ): Response<Messeage>? {
        return dataSource.createOrder(cart_id, shipping_id, address, receiver_name, receiver_phone)
    }
}