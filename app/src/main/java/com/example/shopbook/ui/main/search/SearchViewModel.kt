package com.example.shopbook.ui.main.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.shopbook.data.model.Product
import com.example.shopbook.data.model.ProductState
import com.example.shopbook.data.repository.cart.CartRepository
import com.example.shopbook.data.repository.cart.CartRepositoryImp
import com.example.shopbook.data.repository.search.SearchRepository
import com.example.shopbook.data.repository.search.SearchRepositoryImp
import com.example.shopbook.data.repository.search.historysearch.HistorySearchRepository
import com.example.shopbook.data.repository.search.historysearch.HistorySearchRepositoryImp
import com.example.shopbook.datasource.local.db.entity.ProductDb
import com.example.shopbook.datasource.remote.RemoteDataSource
import kotlinx.coroutines.*

class SearchViewModel(application: Application) : ViewModel() {
    private val _productList = MutableLiveData<ProductState>()
    val productList: LiveData<ProductState> get() = _productList
    private val _historyList = MutableLiveData<List<String>>()
    val historyList: LiveData<List<String>> get() = _historyList

    private val _productNameList = MutableLiveData<List<Product>>()
    val productNameList: LiveData<List<Product>> get() = _productNameList
//    private var productNameList = mutableListOf<String>()

    private var searchRepository: SearchRepository? = SearchRepositoryImp(RemoteDataSource())
    private var cartRepository: CartRepository? = CartRepositoryImp(RemoteDataSource())
    private val historySearchRepository: HistorySearchRepository =
        HistorySearchRepositoryImp(application)

    fun getSearchProducts(
        limit: Int,
        page: Int,
        description_length: Int,
        query_string: String,
        filter_type: Int,
        price_sort_order: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = searchRepository?.getSearchProducts(
                limit,
                page,
                description_length,
                query_string,
                filter_type,
                price_sort_order
            )
            if (response?.isSuccessful == true) {
                if (query_string.isEmpty()) {
                    _productList.postValue(ProductState(response.body()?.products, true))
                } else {
                    _productList.postValue(ProductState(response.body()?.products, false))
                }
            } else {
                Log.d("SearchProduct", "NULLLL")
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

    var job: Job? = null
    fun getHistorySearchLocal(idCustomer: Int) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            val response = historySearchRepository.getAllProducts(idCustomer)
            _historyList.postValue(response)
        }
    }

    fun insertHistorySearchLocal(product: ProductDb) {
        viewModelScope.launch(Dispatchers.IO) {
            historySearchRepository.insertProduct(product)
        }
    }

    fun deleteHistorySearchLocal() {
        viewModelScope.launch(Dispatchers.IO) {
            historySearchRepository.deleteAllProducts()
        }
    }

    fun removeItemHistorySearchLocal(productName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            historySearchRepository.deleteColName(productName)
        }
    }

    fun getSearchHistory(queryString: String) {
        job?.cancel()
        job = viewModelScope.launch(Dispatchers.IO) {
            val response = searchRepository?.getSearchHistory(queryString)
            if (response?.isSuccessful == true) {
                _productNameList.postValue(response.body()?.products)
            } else {
                Log.d("SearchHistory", "NULL")
            }
        }
    }
}