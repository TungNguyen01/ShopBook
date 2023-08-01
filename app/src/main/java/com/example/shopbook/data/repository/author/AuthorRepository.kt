package com.example.shopbook.data.repository.author

import com.example.shopbook.data.model.AuthorList
import retrofit2.Response

interface AuthorRepository {
    suspend fun getAuthor() : Response<AuthorList>?
}