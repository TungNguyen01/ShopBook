package com.example.shopbook.ui.auth.signin.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopbook.data.model.LoginResponse
import com.example.shopbook.data.repository.auth.AuthRepository
import com.example.shopbook.data.repository.auth.AuthRepositoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SignInViewModel : ViewModel() {
    private val authRepository : AuthRepository = AuthRepositoryImp()
    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> get() = _loginSuccess
    private val _accessToken = MutableLiveData<String>()
    val accessToken: LiveData<String>get() = _accessToken
    fun performLogin(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val loginResponse = authRepository.login(email, password)
                val response : LoginResponse? = loginResponse.body()
                Log.d("tung", response.toString())
                withContext(Dispatchers.Main) {
                    if (response != null) {
                        _loginSuccess.postValue(true)
                        _accessToken.postValue(response.accessToken)
                    } else {
                    }
                }
            } catch (e: Exception) {
                e.localizedMessage?.let { Log.d("tung", it) }
                withContext(Dispatchers.Main) {
                }
            }
        }
    }
}