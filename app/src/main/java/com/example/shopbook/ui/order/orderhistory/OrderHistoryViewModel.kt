package com.example.shopbook.ui.order.orderhistory

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopbook.data.model.Order
import com.example.shopbook.data.repository.order.OrderRepositoryImp
import com.example.shopbook.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderHistoryViewModel : ViewModel() {
    private val _orderHistory = MutableLiveData<List<Order>>()
    val orderHistory: MutableLiveData<List<Order>> get() = _orderHistory
    private var orderHistoryRepository: OrderRepositoryImp = OrderRepositoryImp(RemoteDataSource())
    fun getOrderHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = orderHistoryRepository.getOrderHistory()
            if (response?.isSuccessful == true) {
                _orderHistory.postValue(response.body()?.orders)
            } else {
                Log.d("NULLL", "NULLL")
            }
        }
    }
}