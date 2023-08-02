package com.example.shopbook.ui.main.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopbook.data.model.Product
import com.example.shopbook.data.model.ProductList
import com.example.shopbook.data.model.ProductNew
import com.example.shopbook.data.repository.search.SearchRepository
import com.example.shopbook.data.repository.search.SearchRepositoryImp
import com.example.shopbook.datasource.remote.RemoteDataSource
import kotlinx.coroutines.*
import java.util.*

class SearchViewModel() : ViewModel() {
    private var bookList = mutableListOf<Product>()
    private val historyList = mutableListOf<Product>()


//    init {
//        bookList.add(
//            Product(1, "Đắc nhân tâm", "Descriptisfdson", "1000VND",
//                "200VND", "https://cdn0.fahasa.com/media/catalog/product/h/o/hoi-chung-tuoi-thanh-xuan_9_ban-pho-thong.jpg", "", "", 0, 0, 0))
//        bookList.add(Product(3, "Đắc đạo", "Descriptisfdson", "1000VND",
//            "200VND", "https://cdn0.fahasa.com/media/catalog/product/8/9/8935280913738-dd.jpg", "", "", 0, 0, 0))
//        bookList.add(Product(2, "Đắc văn kỉ tử", "Descriptisfdson", "1000VND",
//            "200VND", "https://cdn0.fahasa.com/media/catalog/product/8/9/8935280913738-dd.jpg", "", "", 0, 0, 0))
//        bookList.add(
//            Product(1, "Đắc nhân tâm", "Descriptisfdson", "1000VND",
//                "200VND", "https://cdn0.fahasa.com/media/catalog/product/h/o/hoi-chung-tuoi-thanh-xuan_9_ban-pho-thong.jpg", "", "", 0, 0, 0))
//        bookList.add(Product(3, "Đắc đạo", "Descriptisfdson", "1000VND",
//            "200VND", "https://cdn0.fahasa.com/media/catalog/product/8/9/8935280913738-dd.jpg", "", "", 0, 0, 0))
//        bookList.add(Product(2, "Đắc văn kỉ tử", "Descriptisfdson", "1000VND",
//            "200VND", "https://cdn0.fahasa.com/media/catalog/product/8/9/8935280913738-dd.jpg", "", "", 0, 0, 0))
//        bookList.add(
//            Product(1, "Đắc nhân tâm", "Descriptisfdson", "1000VND",
//                "200VND", "https://cdn0.fahasa.com/media/catalog/product/h/o/hoi-chung-tuoi-thanh-xuan_9_ban-pho-thong.jpg", "", "", 0, 0, 0))
//        bookList.add(Product(3, "Đắc đạo", "Descriptisfdson", "1000VND",
//            "200VND", "https://cdn0.fahasa.com/media/catalog/product/8/9/8935280913738-dd.jpg", "", "", 0, 0, 0))
//        bookList.add(Product(2, "Đắc văn kỉ tử", "Descriptisfdson", "1000VND",
//            "200VND", "https://cdn0.fahasa.com/media/catalog/product/8/9/8935280913738-dd.jpg", "", "", 0, 0, 0))
//        bookList.add(
//            Product(1, "Đắc nhân tâm", "Descriptisfdson", "1000VND",
//                "200VND", "https://cdn0.fahasa.com/media/catalog/product/8/9/8935280913738-dd.jpg", "", "", 0, 0, 0))
//    }
//
//    fun getProducts(): MutableList<Product> {
//        return bookList
//    }
//
//    fun getHistory(): MutableList<Product> {
//        return historyList
//    }

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> get() = _productList
    private val _productNewList = MutableLiveData<List<ProductNew>>()
    val productNewList: LiveData<List<ProductNew>> get() = _productNewList

    //    val productList:MutableList<Product> get() = _productList
    private var searchRepository: SearchRepository? = SearchRepositoryImp(RemoteDataSource())
    fun getAllProducts() {

        viewModelScope.launch(Dispatchers.IO) {
            val response = searchRepository?.getSearchProducts(10, 1, 100, "the gioi", 1, "asc")
            if (response?.isSuccessful == true) {
                _productList.postValue(response.body()?.products)
            } else {
                Log.d("NULLL", "NULLLL")
            }
        }
    }

    fun getSearchNewProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = searchRepository?.getSearchNewProduct()
            if (response?.isSuccessful == true) {
                _productNewList.postValue(response.body()?.productsNew)
                Log.d("PRODUCTLIST", response.body()?.productsNew.toString())
            } else {
                Log.d("NULL", "NULL")
            }
        }
    }
}