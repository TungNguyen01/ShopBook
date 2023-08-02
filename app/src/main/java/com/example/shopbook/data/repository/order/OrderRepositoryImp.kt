package com.example.shopbook.data.repository.order

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
}