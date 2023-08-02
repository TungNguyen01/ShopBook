package com.example.shopbook.ui.order.orderdetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopbook.data.model.OrderDetail
import com.example.shopbook.data.model.OrderDetailProduct
import com.example.shopbook.data.repository.order.OrderRepositoryImp
import com.example.shopbook.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderDetailViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _orderDetailList = MutableLiveData<OrderDetail>()
    val orderDetailList:MutableLiveData<OrderDetail> get() = _orderDetailList
    private var orderRepository:OrderRepositoryImp=OrderRepositoryImp(RemoteDataSource())

    fun getOrderDetails(orderId:Int) {
        viewModelScope.launch(Dispatchers.IO){
            val response=orderRepository.getOrderDetail(orderId)
            if(response?.isSuccessful==true){
                _orderDetailList.postValue(response.body())
            }else{
                Log.d("OrderDetailNULL", "NULL")
            }
        }
    }
}