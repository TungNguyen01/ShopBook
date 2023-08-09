package com.example.shopbook.ui.main.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopbook.data.model.Product
import com.example.shopbook.data.model.ProductList
import com.example.shopbook.data.model.ProductNew
import com.example.shopbook.data.repository.cart.CartRepository
import com.example.shopbook.data.repository.cart.CartRepositoryImp
import com.example.shopbook.data.repository.search.SearchRepository
import com.example.shopbook.data.repository.search.SearchRepositoryImp
import com.example.shopbook.datasource.remote.RemoteDataSource
import kotlinx.coroutines.*
import java.util.*

class SearchViewModel() : ViewModel() {
    private var bookList = mutableListOf<Product>()
    private val _productHistoryList = MutableLiveData<List<Product>>()
    val productHistoryList: LiveData<List<Product>> get() = _productHistoryList
    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> get() = _productList
    private val _productNewList = MutableLiveData<List<ProductNew>>()
    val productNewList: LiveData<List<ProductNew>> get() = _productNewList

    //    val productList:MutableList<Product> get() = _productList
    private var searchRepository: SearchRepository? = SearchRepositoryImp(RemoteDataSource())
    private var cartRepository: CartRepository? = CartRepositoryImp(RemoteDataSource())
    fun getSearchProducts(limit: Int,
                       page: Int,
                       description_length: Int,
                       query_string: String,
                       filter_type: Int,
                       price_sort_order: String,) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = searchRepository?.getSearchProducts(limit, page, description_length, query_string, filter_type, price_sort_order)
            if (response?.isSuccessful == true) {
                _productList.postValue(response.body()?.products)
            } else {
                Log.d("NULLL", "NULLLL")
            }
        }
    }

    fun getSearchHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = searchRepository?.getSearchProducts(10, 1, 100,"", 0, "asc")
            if (response?.isSuccessful == true) {
                _productHistoryList.postValue(response.body()?.products)
                Log.d("PRODUCTLIST", response.body()?.products.toString())
            } else {
                Log.d("NULL", "NULL")
            }
        }
    }
    fun addItemToCart(productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = cartRepository?.addCartItem(productId)
            if (response?.isSuccessful == true) {
                Log.d("SUCCESSFUL", "OK")
            } else {
                Log.d("ADDITEMTOCARTNULL", "NULL")
            }
        }
    }
}