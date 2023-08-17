package com.example.shopbook.ui.publisher

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopbook.data.model.Banner
import com.example.shopbook.data.model.ProductState
import com.example.shopbook.data.model.Supply
import com.example.shopbook.data.repository.banner.BannerRepository
import com.example.shopbook.data.repository.banner.BannerRepositoryImp
import com.example.shopbook.data.repository.cart.CartRepository
import com.example.shopbook.data.repository.cart.CartRepositoryImp
import com.example.shopbook.data.repository.supply.SupplyRepositoryImp
import com.example.shopbook.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PublisherViewModel : ViewModel() {

    private var _productList = MutableLiveData<ProductState>()
    val producList: LiveData<ProductState> get() = _productList
    private var supplyRepository: SupplyRepositoryImp? = SupplyRepositoryImp(RemoteDataSource())
    private var cartRepository: CartRepository? = CartRepositoryImp(RemoteDataSource())

    //private var productRepository: ProductRepository? = ProductRepositoryImp(RemoteDataSource())
    fun getSuplly(id: Int, limit: Int, page: Int, description_length: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val response = supplyRepository?.getSupply(id, limit, page, description_length)
            Log.d("tungnui", response?.body().toString())
            if(response?.isSuccessful == true){
                //_supply.postValue(response.body()?.suplly)
                _productList.postValue(ProductState(response.body()?.products, true))
            }
        }
    }
    fun addItemToCart(productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = cartRepository?.addCartItem(productId)
            if (response?.isSuccessful == true) {
                Log.d("SUCCESSFUL", "OK")
            }
        }
    }
    fun getSearchSupply(supplyId: Int, currentPage: Int, queryString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = supplyRepository?.getSearchSupply(supplyId,10, currentPage, 100, queryString)
            if (response?.isSuccessful == true) {
                _productList.postValue(ProductState(response.body()?.products, false))
            }
        }
    }

}