package com.example.shopbook.ui.main.shoppingbag.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopbook.data.api.RetrofitClient.apiService
import com.example.shopbook.data.model.CartItemBag
import com.example.shopbook.data.model.Messeage
import com.example.shopbook.data.repository.cart.CartRepository
import com.example.shopbook.data.repository.cart.CartRepositoryImp
import com.example.shopbook.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ShoppingbagViewModel : ViewModel() {

    private val _cart = MutableLiveData<List<CartItemBag>>()
    val cart : LiveData<List<CartItemBag>> get() = _cart
    private var cartRepository: CartRepository? = CartRepositoryImp(RemoteDataSource())
    private val _idCart = MutableLiveData<String>()
    val idCart : LiveData<String> get() = _idCart
    private val _messageRemove = MutableLiveData<Messeage>()
    val messeageRemove: MutableLiveData<Messeage> get() = _messageRemove
    private val _messageUpdate = MutableLiveData<Messeage>()
    val messeageUpdate: MutableLiveData<Messeage> get() = _messageUpdate
    fun getCart(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = cartRepository?.getCart()
            if(response?.isSuccessful == true){
                _cart.postValue(response.body()?.products)
               // _idCart.postValue(response.body()?.cart_id)
            }
        }
    }
    fun deleteProduct(itemId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = cartRepository?.removeProduct(itemId)
            Log.d("tungnuii", response?.body().toString())
            if (response?.isSuccessful == true) {
                _messageRemove.postValue(response.body())
            }
        }
    }
    fun updateQuantity(itemId: Int, quantity: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = cartRepository?.updateQuantity(itemId, quantity)
            Log.d("sontung", response?.body().toString())
            if (response?.isSuccessful == true) {
                _messageUpdate.postValue(response.body())
            }
        }
    }
}
