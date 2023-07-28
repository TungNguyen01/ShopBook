package com.example.shopbook.data.repository.auth
import com.example.shopbook.data.api.RetrofitClient
import com.example.shopbook.data.model.LoginResponse
import retrofit2.Response

class AuthRepositoryImp : AuthRepository {
    override suspend fun login(email: String, password: String): Response<LoginResponse> {
        return RetrofitClient.apiService.login(email, password)
    }
}