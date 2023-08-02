package com.example.shopbook.data.api

import com.example.shopbook.data.model.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST("customers/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<LoginResponse>
    @FormUrlEncoded
    @POST("customers")
    fun register(
        @Field("email") email: String,
        @Field("name") name: String,
        @Field("password") password: String,
    ): Call<AccessTokenResponse>
    @FormUrlEncoded
    @POST("customers/forgotPass")
    suspend fun forgot(
        @Field("email") email : String,
    ): Response<ForgotResponse>

    @GET("products")
    suspend fun getProducts(): Response<ProductList>
    @GET("products/hot")
    suspend fun getHotBook(): Response<HotBookList>
    @GET("category")
    suspend fun getCategory(): Response<CategoryList>

    @GET("products/new")
    suspend fun getNewBook(): Response<NewArrivalList>

    @GET("author/hot")
    suspend fun getAuthor(): Response<AuthorList>

    @GET("products/search")
    suspend fun getSearchProducts(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("description_length") descriptionLength: Int,
        @Query("query_string") queryString: String,
        @Query("filter_type") filterType: Int,
        @Query("price_sort_order") priceSortOrder: String,
    ): Response<ProductList>

    @GET("products/author/search")
    suspend fun getSearchAuthorProducts(
        @Query("author_id") authorId: Int,
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("description_length") descriptionLength: Int,
        @Query("query_string") queryString: String,
    ): Response<ProductList>

    @Headers("user-key: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b21lcl9pZCI6MiwibmFtZSI6IlR1YW4gQW5oIiwiZW1haWwiOiJhbmhkdDFAeW9wbWFpbC5jb20iLCJpYXQiOjE2ODk4NDM5NTMsImV4cCI6MTY5MTEzOTk1M30.Hg_DXIPz_uDjBbX8d5khpFWdu0or6Xxd8Ij-k_pOcuA")
    @GET("products/{product_id}")
    suspend fun getProductInfo(@Path("product_id") product_id: Int): Response<ProductInfoList>

    @GET("products/author")
    suspend fun getProductsByAuthor(
        @Query("author_id") author_id: Int,
        @Query("limit") limit: Int,
        @Query("description_length") description_length: Int,
    ): Response<ProductsByAuthor>

    @GET("author/{authorId}")
    suspend fun getAuthor(@Path("authorId") authorId: Int): Response<AuthorResult>

    @GET("products/new")
    suspend fun getSearchNewProduct(): Response<ProductNewList>

    @Headers("user-key: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b21lcl9pZCI6MiwibmFtZSI6IlR1YW4gQW5oIiwiZW1haWwiOiJhbmhkdDFAeW9wbWFpbC5jb20iLCJpYXQiOjE2ODk4NDM5NTMsImV4cCI6MTY5MTEzOTk1M30.Hg_DXIPz_uDjBbX8d5khpFWdu0or6Xxd8Ij-k_pOcuA")
    @GET("customers")
    suspend fun getCustomer(): Response<Customer>

    @FormUrlEncoded
    @Headers("user-key: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b21lcl9pZCI6MiwibmFtZSI6IlR1YW4gQW5oIiwiZW1haWwiOiJhbmhkdDFAeW9wbWFpbC5jb20iLCJpYXQiOjE2ODk4NDM5NTMsImV4cCI6MTY5MTEzOTk1M30.Hg_DXIPz_uDjBbX8d5khpFWdu0or6Xxd8Ij-k_pOcuA")
    @PUT("customers")
    suspend fun updateCustomer(
        @Field("name") name: String,
        @Field("address") address: String,
        @Field("date_of_birth") dateofbirth: String,
        @Field("gender") gender: String,
        @Field("mob_phone") mobphone: String,
    ): Response<Customer>

    @FormUrlEncoded
    @POST("customers/changePass")
    suspend fun changePassword(
        @Field("email") email: String,
        @Field("old_password") old_password: String,
        @Field("new_password") new_password: String,
    ): Response<Customer>

    @Multipart
    @Headers("user-key: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b21lcl9pZCI6MiwibmFtZSI6IlR1YW4gQW5oIiwiZW1haWwiOiJhbmhkdDFAeW9wbWFpbC5jb20iLCJpYXQiOjE2ODk4NDM5NTMsImV4cCI6MTY5MTEzOTk1M30.Hg_DXIPz_uDjBbX8d5khpFWdu0or6Xxd8Ij-k_pOcuA")
    @POST("customers/update/avatar")
    suspend fun changeAvatar(
        @Part image: MultipartBody.Part,
    ): Response<Customer>

    @Headers("user-key: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b21lcl9pZCI6MiwibmFtZSI6IlR1YW4gQW5oIiwiZW1haWwiOiJhbmhkdDFAeW9wbWFpbC5jb20iLCJpYXQiOjE2ODk4NDM5NTMsImV4cCI6MTY5MTEzOTk1M30.Hg_DXIPz_uDjBbX8d5khpFWdu0or6Xxd8Ij-k_pOcuA")
    @GET("orders")
    suspend fun getOrderHistory(): Response<OrderList>

    @Headers("user-key: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXN0b21lcl9pZCI6MiwibmFtZSI6IlR1YW4gQW5oIiwiZW1haWwiOiJhbmhkdDFAeW9wbWFpbC5jb20iLCJpYXQiOjE2ODk4NDM5NTMsImV4cCI6MTY5MTEzOTk1M30.Hg_DXIPz_uDjBbX8d5khpFWdu0or6Xxd8Ij-k_pOcuA")
    @GET("orders/{orderId}")
    suspend fun getOrderDetail(@Path("orderId") orderId: Int): Response<OrderDetail>


}