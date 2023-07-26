package com.example.shopbook.data.model

data class Product(
    var product_it : Int,
    val name: String?,
    var description: String?,
    var price: String?,
    var discount: String?,
    var image: String?,
    val image_2: String?,
    var thumbnail: String?,
    var display: Int=0,
    var author_id: Int,
    var supplier_id: Int
)
