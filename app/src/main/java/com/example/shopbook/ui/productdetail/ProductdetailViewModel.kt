package com.example.shopbook.ui.productdetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopbook.data.model.Author
import com.example.shopbook.data.model.ProductInfo
import com.example.shopbook.data.model.ProductInfoList
import com.example.shopbook.data.model.Supplier
import com.example.shopbook.data.repository.product.ProductRepository
import com.example.shopbook.data.repository.product.ProductRepositoryImp
import com.example.shopbook.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductdetailViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _productListInfo = MutableLiveData<ProductInfoList?>()
    val productInfo: MutableLiveData<ProductInfoList?> get() = _productListInfo

    private var productRepository: ProductRepository? = ProductRepositoryImp(RemoteDataSource())
    fun getProductInfo(id: Int) {

        viewModelScope.launch(Dispatchers.IO) {

            val response = productRepository?.getProductInfo(id)
            if (response?.isSuccessful == true) {
//                _productInfo.postValue(response.body()?.product)
//                _supplierInfo.postValue(response.body()?.supplier)
//                _authorInfo.postValue(response.body()?.author)
                _productListInfo.postValue(response.body())
            } else {
                Log.d("NNULLL", "NULLLL")
            }
        }
    }
}