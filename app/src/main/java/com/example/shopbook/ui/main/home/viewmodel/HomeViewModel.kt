package com.example.shopbook.ui.main.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopbook.data.model.*
import com.example.shopbook.data.repository.author.AuthorRepository
import com.example.shopbook.data.repository.author.AuthorRepositoryImp
import com.example.shopbook.data.repository.category.CategoryRepository
import com.example.shopbook.data.repository.category.CategoryRepositoryImp
import com.example.shopbook.data.repository.hotbook.HotBookRepository
import com.example.shopbook.data.repository.hotbook.HotBookRepositoryImp
import com.example.shopbook.data.repository.newbook.NewBookRepository
import com.example.shopbook.data.repository.newbook.NewBookRepositoryImp
import com.example.shopbook.data.repository.product.ProductRepository
import com.example.shopbook.data.repository.product.ProductRepositoryImp
import com.example.shopbook.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    //private var hotbook = mutableListOf<HotBook>()

    private var category = mutableListOf<Category>()
//    fun getHotBooks() : MutableList<HotBook>{
//        return hotbook
//    }
    private val _hotbookList = MutableLiveData<List<HotBook>>()
    val hotBookList: LiveData<List<HotBook>> get() = _hotbookList
    private var hotbookRepository: HotBookRepository? = HotBookRepositoryImp(RemoteDataSource())
    fun getAllHotBook() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = hotbookRepository?.getHotBook()
            Log.d("tung", (response?.body()).toString())
            if (response?.isSuccessful == true) {
                _hotbookList.postValue(response.body()?.hotBook)
            } else {
                //Log.d("tung", "loi")
            }
        }
    }
    private val _categoryList = MutableLiveData<List<Category>>()
    val categoryList: LiveData<List<Category>> get() = _categoryList
    private var categotyRepository: CategoryRepository? = CategoryRepositoryImp(RemoteDataSource())
    fun getAllCategory(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = categotyRepository?.getCategory()
            Log.d("tung", (response?.body()).toString())
            if(response?.isSuccessful == true){
                _categoryList.postValue(response.body()?.categories)
            }
        }
    }
    private val _newbookList = MutableLiveData<List<NewArrival>>()
    val newbookList: LiveData<List<NewArrival>> get() = _newbookList
    private var newBookRepository: NewBookRepository? = NewBookRepositoryImp(RemoteDataSource())
    fun getAllNewBook(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = newBookRepository?.getNewBook()
            Log.d("tung", (response?.body()).toString())
            if (response?.isSuccessful == true){
                _newbookList.postValue(response.body()?.newBook)
            }
        }
    }
    private val _authorList = MutableLiveData<List<Author>>()
    val authorList: LiveData<List<Author>> get() = _authorList
    private var authorRepository: AuthorRepository? = AuthorRepositoryImp(RemoteDataSource())

    fun getAllAuthor(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = authorRepository?.getAllAuthors()
            Log.d("tung", (response?.body()).toString())
            if(response?.isSuccessful == true){
                _authorList.postValue(response.body()?.authors)
            }
        }
    }
    private val _productListInfo = MutableLiveData<ProductInfoList?>()
    val productInfo: MutableLiveData<ProductInfoList?> get() = _productListInfo

    private var productRepository: ProductRepository? = ProductRepositoryImp(RemoteDataSource())
    fun getProductInfo(id: Int) {

        viewModelScope.launch(Dispatchers.IO) {

            val response = productRepository?.getProductInfo(id)
            Log.d("tungnui", (response?.body()).toString())
            if (response?.isSuccessful == true) {
                _productListInfo.postValue(response.body())
            }
        }
    }
}