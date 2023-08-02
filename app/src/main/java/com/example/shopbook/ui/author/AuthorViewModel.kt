package com.example.shopbook.ui.author

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopbook.data.model.Author
import com.example.shopbook.data.model.AuthorResult
import com.example.shopbook.data.model.Product
import com.example.shopbook.data.repository.author.AuthorRepository
import com.example.shopbook.data.repository.author.AuthorRepositoryImp
import com.example.shopbook.data.repository.product.ProductRepository
import com.example.shopbook.data.repository.product.ProductRepositoryImp
import com.example.shopbook.data.repository.search.SearchRepository
import com.example.shopbook.data.repository.search.SearchRepositoryImp
import com.example.shopbook.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthorViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> get() = _productList
    private val _author = MutableLiveData<AuthorResult>()
    val author: LiveData<AuthorResult> get() = _author

    private var productRepository: ProductRepository? = ProductRepositoryImp(RemoteDataSource())
    private var authorRepository: AuthorRepository? = AuthorRepositoryImp(RemoteDataSource())
    private var searchRepository: SearchRepository? = SearchRepositoryImp(RemoteDataSource())
    fun getProductsByAuthor(author_id: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            val response = productRepository?.getProductsByAuthor(author_id, 20, 100)
            if (response?.isSuccessful == true) {
                _productList.postValue(response.body()?.products)
            } else {
                Log.d("NNULLL", "NULLLL")
            }
        }
    }

    fun getSearchAuthorProduct(authorId: Int, queryString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response =
                searchRepository?.getSearchAuthorProducts(authorId, 10, 1, 100, queryString)
            if (response?.isSuccessful == true) {
                _productList.postValue(response.body()?.products)
            } else {
                Log.d("NNULLL", "NULLLL")
            }
        }
    }

    fun getAuthor(authorId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = authorRepository?.getAuthor(authorId)
            if (response?.isSuccessful == true) {
                _author.postValue(response.body())
            } else {
                Log.d("AUTHORNULL", "NULL")
            }
        }
    }
}