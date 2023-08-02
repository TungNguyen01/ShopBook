package com.example.shopbook.data.repository.author

import com.example.shopbook.data.model.Author
import com.example.shopbook.data.model.AuthorList
import com.example.shopbook.data.model.AuthorResult
import retrofit2.Response

interface AuthorRepository {


    suspend fun getAllAuthors() : Response<AuthorList>?
    suspend fun getAuthor(authorId: Int): Response<AuthorResult>?
}