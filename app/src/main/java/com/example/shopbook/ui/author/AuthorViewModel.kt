package com.example.shopbook.ui.author

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopbook.data.model.Author
import com.example.shopbook.data.model.AuthorResult
import com.example.shopbook.data.model.Product
import com.example.shopbook.data.model.ProductState
import com.example.shopbook.data.repository.author.AuthorRepository
import com.example.shopbook.data.repository.author.AuthorRepositoryImp
import com.example.shopbook.data.repository.cart.CartRepository
import com.example.shopbook.data.repository.cart.CartRepositoryImp
import com.example.shopbook.data.repository.product.ProductRepository
import com.example.shopbook.data.repository.product.ProductRepositoryImp
import com.example.shopbook.data.repository.search.SearchRepository
import com.example.shopbook.data.repository.search.SearchRepositoryImp
import com.example.shopbook.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AuthorViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _productList = MutableLiveData<ProductState>()
    val productList: LiveData<ProductState> get() = _productList
    private val _author = MutableLiveData<AuthorResult>()
    val author: LiveData<AuthorResult> get() = _author

    private var productRepository: ProductRepository? = ProductRepositoryImp(RemoteDataSource())
    private var authorRepository: AuthorRepository? = AuthorRepositoryImp(RemoteDataSource())
    private var searchRepository: SearchRepository? = SearchRepositoryImp(RemoteDataSource())
    private var cartRepository: CartRepository? = CartRepositoryImp(RemoteDataSource())
    fun getProductsByAuthor(authorId: Int, limit: Int, page: Int, desLength: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = productRepository?.getProductsByAuthor(authorId, limit, page, desLength)
            if (response?.isSuccessful == true) {
                _productList.postValue(ProductState(response.body()?.products, true))
            } else {
                Log.d("NNULLL", "NULLLL")
            }
        }
    }

    fun getSearchAuthorProduct(authorId: Int, currentPage: Int, queryString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response =
                searchRepository?.getSearchAuthorProducts(
                    authorId,
                    10,
                    currentPage,
                    100,
                    queryString
                )
            if (response?.isSuccessful == true) {
                _productList.postValue(ProductState(response.body()?.products, false))
            } else {
                Log.d("searchAuthor", "NULLLL")
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

    fun addItemToCart(productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = cartRepository?.addCartItem(productId)
            Log.d("FAILL", response?.body().toString())
            if (response?.isSuccessful == true) {
                Log.d("SUCCESSFUL", "OK")
            } else {
                Log.d("NULL", "NULL")
            }
        }
    }
}