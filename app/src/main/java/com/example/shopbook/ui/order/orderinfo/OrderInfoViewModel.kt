package com.example.shopbook.ui.order.orderinfo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopbook.data.model.Customer
import com.example.shopbook.data.repository.user.UserRepository
import com.example.shopbook.data.repository.user.UserRepositoryImp
import com.example.shopbook.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderInfoViewModel : ViewModel() {
    private val _user = MutableLiveData<Customer>()
    val user: LiveData<Customer> get() = _user
    private var userRepository: UserRepository? = UserRepositoryImp(RemoteDataSource())
    fun getCustomer() {

        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository?.getCustomer()
            if (response?.isSuccessful == true) {
                _user.postValue(response.body())
            }
        }
    }
    fun updateCustomer(name:String, address:String,mob_phone:String){
        viewModelScope.launch(Dispatchers.IO){
            val response = userRepository?.updateInformation(name, address,mob_phone)
            if(response?.isSuccessful==true){
                _user.postValue(response.body())
            }
        }
    }
}