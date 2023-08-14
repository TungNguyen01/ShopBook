package com.example.shopbook.data.repository.search.historysearch

import com.example.shopbook.datasource.local.db.entity.ProductDb

interface HistorySearchRepository {

    fun getAllProducts(idCustomer: Int): List<String>
    fun insertProduct(product: ProductDb)
    fun deleteAllProducts()
    fun deleteColName(productName: String)
    fun getAllProductName(query: String): List<String>
}