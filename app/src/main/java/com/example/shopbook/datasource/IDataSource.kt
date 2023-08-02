package com.example.shopbook.datasource

import com.example.shopbook.data.model.*
import okhttp3.MultipartBody
import retrofit2.Response

interface IDataSource {

    suspend fun getBooks(): Response<HotBookList>?

    suspend fun getCategory() : Response<CategoryList>?

    suspend fun getNewBook() : Response<NewArrivalList>?

    suspend fun getAllAuthor() : Response<AuthorList>?

    suspend fun getSearchProducts(
        limit: Int,
        page: Int,
        description_length: Int,
        query_string: String,
        filter_type: Int,
        price_sort_order: String,
    ): Response<ProductList>?

    suspend fun getSearchAuthorProducts(
        authorId: Int,
        limit: Int,
        page: Int,
        description_length: Int,
        query_string: String,
    ): Response<ProductList>?

    suspend fun getProductInfo(id: Int): Response<ProductInfoList>?
    suspend fun getProductsByAuthor(
        id: Int,
        limit: Int,
        description_length: Int,
    ): Response<ProductsByAuthor>?

    suspend fun getAuthor(authorId: Int): Response<AuthorResult>?
    suspend fun getSearchNewProduct(): Response<ProductNewList>?
    suspend fun getCustomer(): Response<Customer>?
    suspend fun updateCustomer(
        name: String,
        address: String,
        dob: String,
        gender: String,
        mob_phone: String,
    ): Response<Customer>?

    suspend fun changePassword(
        email: String, old_password: String,
        new_password: String,
    ): Response<Customer>?

    suspend fun changeAvatar(image: MultipartBody.Part): Response<Customer>?
    suspend fun getOrderHistory(): Response<OrderList>?
    suspend fun getOrderDetail(orderId: Int): Response<OrderDetail>?
}