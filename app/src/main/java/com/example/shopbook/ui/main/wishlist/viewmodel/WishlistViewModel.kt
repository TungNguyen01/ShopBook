package com.example.shopbook.ui.main.wishlist.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopbook.data.model.Messeage
import com.example.shopbook.data.model.Wishlist
import com.example.shopbook.data.repository.cart.CartRepositoryImp
import com.example.shopbook.data.repository.wishlist.WishListRepositoryImp
import com.example.shopbook.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WishlistViewModel : ViewModel() {
    private val _messageRemove = MutableLiveData<Messeage>()
    val messeageRemove: MutableLiveData<Messeage> get() = _messageRemove
    private val _wishlist = MutableLiveData<List<Wishlist>>()
    val wishlist : MutableLiveData<List<Wishlist>> get() = _wishlist
    private var wishListRepository: WishListRepositoryImp? = WishListRepositoryImp(RemoteDataSource())
    private var cartRepository: CartRepositoryImp? = CartRepositoryImp(RemoteDataSource())
    fun removeItemInWishList(productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = wishListRepository?.removeItemInWishList(productId)
            if (response?.isSuccessful == true) {
                _messageRemove.postValue(response.body())
            }
        }
    }
    fun getWishlist(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = wishListRepository?.getWishlist()
            if(response?.isSuccessful == true){
                _wishlist.postValue(response.body()?.wishlist)
            }
        }
    }
    fun addItemToCart(productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = cartRepository?.addCartItem(productId)
            if (response?.isSuccessful == true) {
                Log.d("tung", "Thanh cong")
            }
        }
    }
}