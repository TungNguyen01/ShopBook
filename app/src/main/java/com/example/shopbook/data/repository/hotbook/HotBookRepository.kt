package com.example.shopbook.data.repository.hotbook

import com.example.shopbook.data.model.HotBookList
import retrofit2.Response

interface HotBookRepository {
    suspend fun getHotBook(): Response<HotBookList>?
}