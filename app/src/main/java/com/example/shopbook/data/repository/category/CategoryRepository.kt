package com.example.shopbook.data.repository.category

import com.example.shopbook.data.model.CategoryList
import com.example.shopbook.data.model.HotBookList
import retrofit2.Response

interface CategoryRepository {
    suspend fun getCategory(): Response<CategoryList>?
}