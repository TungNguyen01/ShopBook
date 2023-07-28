package com.example.shopbook.data.repository.auth

import com.example.shopbook.data.model.LoginResponse
import retrofit2.Response

interface AuthRepository {
    suspend fun login(email: String, password: String): Response<LoginResponse>
}