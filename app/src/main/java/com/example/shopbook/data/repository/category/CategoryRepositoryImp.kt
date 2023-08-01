package com.example.shopbook.data.repository.category

import com.example.shopbook.data.model.CategoryList
import com.example.shopbook.datasource.IDataSource
import retrofit2.Response

class CategoryRepositoryImp(private val dataSource: IDataSource) : CategoryRepository{
    override suspend fun getCategory(): Response<CategoryList>? {
        return dataSource.getCategory()
    }
}