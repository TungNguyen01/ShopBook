package com.example.shopbook.data.repository.hotbook

import com.example.shopbook.data.model.HotBookList
import com.example.shopbook.datasource.IDataSource
import retrofit2.Response

class HotBookRepositoryImp(private val dataSource: IDataSource) : HotBookRepository {
    override suspend fun getHotBook(): Response<HotBookList>? {
        return dataSource.getBooks()
    }
}