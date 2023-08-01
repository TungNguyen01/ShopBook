package com.example.shopbook.data.repository.newbook

import com.example.shopbook.data.model.NewArrivalList
import com.example.shopbook.datasource.IDataSource
import retrofit2.Response

class NewBookRepositoryImp(private val dataSource: IDataSource) : NewBookRepository {
    override suspend fun getNewBook(): Response<NewArrivalList>? {
        return dataSource.getNewBook()
    }
}