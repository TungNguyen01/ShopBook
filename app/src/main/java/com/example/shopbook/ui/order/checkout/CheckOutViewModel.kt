package com.example.shopbook.ui.order.checkout

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopbook.data.model.CartItemBag
import com.example.shopbook.data.model.Customer
import com.example.shopbook.data.model.Messeage
import com.example.shopbook.data.repository.cart.CartRepository
import com.example.shopbook.data.repository.cart.CartRepositoryImp
import com.example.shopbook.data.repository.order.OrderRepository
import com.example.shopbook.data.repository.order.OrderRepositoryImp
import com.example.shopbook.data.repository.user.UserRepository
import com.example.shopbook.data.repository.user.UserRepositoryImp
import com.example.shopbook.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CheckOutViewModel : ViewModel() {
    private val _profile = MutableLiveData<Customer>()
    val profile: LiveData<Customer> get() = _profile
    private var userRepository: UserRepository? = UserRepositoryImp(RemoteDataSource())
    fun getCustomer() {

        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository?.getCustomer()
            Log.d("Tung", response?.body().toString())
            if (response?.isSuccessful == true) {
                _profile.postValue(response.body())

            }
        }
    }
    private val _cart = MutableLiveData<List<CartItemBag>>()
    val cart : LiveData<List<CartItemBag>> get() = _cart
    private var cartRepository: CartRepository? = CartRepositoryImp(RemoteDataSource())
    private val _idcart = MutableLiveData<String?>()
    val idcart : LiveData<String?> get() = _idcart

    fun getCart(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = cartRepository?.getCart()
            if(response?.isSuccessful == true){
                _cart.postValue(response.body()?.products)
                _idcart.postValue(response.body()?.cart_id)
            }
           // Log.d("tungnuyen", idcart.value.toString())
        }
    }
    private val _checkout = MutableLiveData<Messeage>()
    val checkout : LiveData<Messeage> get() = _checkout
    private var orderRepository : OrderRepository? = OrderRepositoryImp(RemoteDataSource())
    fun createOrder(cart_id : String, shipping_id: Int, address : String, receiver_name : String, receiver_phone : String){
        viewModelScope.launch(Dispatchers.IO) {
            val response = orderRepository?.createOrder(cart_id, 1, address, receiver_name, receiver_phone)
            if(response?.isSuccessful == true){
                Log.d("tungnguyen", "Thanh Cong")
            } else{
                Log.d("tungnguyen", "Null")
            }
        }
    }
}