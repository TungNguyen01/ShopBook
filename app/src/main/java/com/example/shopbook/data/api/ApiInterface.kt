package com.example.shopbook.data.api

import com.example.shopbook.data.model.*
import com.example.shopbook.utils.MySharedPreferences
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @FormUrlEncoded
    @POST("customers/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
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
        @Field("email") email: String,
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

    @GET("products/search")
    suspend fun getSearchHistory(
        @Query("query_string") queryString: String,
    ): Response<ProductList>

    @GET("products/author/search")
    suspend fun getSearchAuthorProducts(
        @Query("author_id") authorId: Int,
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("description_length") descriptionLength: Int,
        @Query("query_string") queryString: String,
    ): Response<ProductList>

    @GET("products/category/search")
    suspend fun getSearchCategoryProducts(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("description_length") descriptionLength: Int,
        @Query("query_string") queryString: String,
        @Query("category_id") categoryId: Int,
    ): Response<ProductList>

    @GET("products/{product_id}")
    suspend fun getProductInfo(@Path("product_id") product_id: Int): Response<ProductInfoList>

    @GET("products/author")
    suspend fun getProductsByAuthor(
        @Query("author_id") author_id: Int,
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("description_length") description_length: Int,
    ): Response<ProductsByAuthor>

    @GET("products/incategory/{categoryId}")
    suspend fun getProductsByCategory(
        @Path("categoryId") categoryId: Int,
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("description_length") description_length: Int,
    ): Response<ProductList>

    @GET("author/{authorId}")
    suspend fun getAuthor(@Path("authorId") authorId: Int): Response<AuthorResult>

    @GET("products/new")
    suspend fun getSearchNewProduct(): Response<ProductNewList>

    @GET("customers")
    suspend fun getCustomer(): Response<Customer>

    @FormUrlEncoded

    @PUT("customers")
    suspend fun updateCustomer(
        @Field("name") name: String,
        @Field("address") address: String,
        @Field("date_of_birth") dateofbirth: String,
        @Field("gender") gender: String,
        @Field("mob_phone") mobphone: String,
    ): Response<Customer>
    @FormUrlEncoded
    @PUT("customers")
    suspend fun updateInformation(
        @Field("name") name: String,
        @Field("address") address: String,
        @Field("mob_phone") mobphone: String,
    ): Response<Customer>

    @FormUrlEncoded
    @POST("orders")
    suspend fun createOrder(
        @Field("cart_id") cart_id : String,
        @Field("shipping_id") shipping_id : Int,
        @Field("address") address: String,
        @Field("receiver_name") receiver_name : String,
        @Field("receiver_phone") receiver_phone : String,
    ): Response<Messeage>

    @FormUrlEncoded
    @POST("customers/changePass")
    suspend fun changePassword(
        @Field("email") email: String,
        @Field("old_password") old_password: String,
        @Field("new_password") new_password: String,
    ): Response<Customer>

    @Multipart

    @POST("customers/update/avatar")
    suspend fun changeAvatar(
        @Part image: MultipartBody.Part,
    ): Response<Customer>


    @GET("orders")
    suspend fun getOrderHistory(): Response<OrderList>


    @GET("orders/{orderId}")
    suspend fun getOrderDetail(@Path("orderId") orderId: Int): Response<OrderDetail>

    @FormUrlEncoded

    @POST("shoppingCart/add")
    suspend fun addProduct2Cart(@Field("product_id") productId: Int): Response<List<CartItem>>

    @FormUrlEncoded
    @POST("wishlist/add")
    suspend fun addItemToWishList(@Field("product_id") productId: Int): Response<Messeage>


    @DELETE("wishlist/remove/{product_id}")
    suspend fun removeItemInWishList(@Path("product_id") productId: Int): Response<Messeage>

    @GET("wishlist")
    suspend fun getWishlist() : Response<WishlistList>

    @GET("shoppingCart")
    suspend fun getCart(): Response<CartList>

    @DELETE("shoppingCart/removeProduct/{item_id}")
    suspend fun removeProduct(@Path("item_id") itemId: Int): Response<Messeage>

    @FormUrlEncoded
    @POST("shoppingCart/update")
    suspend fun updateQuantity(
        @Field("item_id") itemId: Int,
        @Field("quantity") quantity: Int,
    ): Response<Messeage>


}