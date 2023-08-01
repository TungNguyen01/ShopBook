package com.example.shopbook.data.repository.author

import com.example.shopbook.data.model.AuthorList
import com.example.shopbook.datasource.IDataSource
import retrofit2.Response

class AuthorRepositoryImp(private val dataSource: IDataSource): AuthorRepository {
    override suspend fun getAuthor(): Response<AuthorList>? {
        return dataSource.getAuthor()
    }
}