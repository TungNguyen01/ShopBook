package com.example.shopbook.data.repository.author

import com.example.shopbook.data.model.Author
import com.example.shopbook.data.model.AuthorList
import com.example.shopbook.data.model.AuthorResult
import com.example.shopbook.datasource.IDataSource
import retrofit2.Response

class AuthorRepositoryImp(private val iDataSource: IDataSource) : AuthorRepository {

    override suspend fun getAllAuthors(): Response<AuthorList>? {
        return iDataSource.getAllAuthor()
    }
    override suspend fun getAuthor(authorId: Int): Response<AuthorResult>? {
        return iDataSource.getAuthor(authorId)
    }
}