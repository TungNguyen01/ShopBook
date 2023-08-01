package com.example.shopbook.data.repository.newbook

import com.example.shopbook.data.model.NewArrivalList
import retrofit2.Response

interface NewBookRepository {
    suspend fun getNewBook(): Response<NewArrivalList>?
}