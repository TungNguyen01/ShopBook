package com.example.shopbook.ui.profile

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopbook.data.model.Customer
import com.example.shopbook.data.repository.search.historysearch.HistorySearchRepository
import com.example.shopbook.data.repository.search.historysearch.HistorySearchRepositoryImp
import com.example.shopbook.data.repository.user.UserRepository
import com.example.shopbook.data.repository.user.UserRepositoryImp
import com.example.shopbook.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : ViewModel() {
    // TODO: Implement the ViewModel
    private val _profile = MutableLiveData<Customer>()
    val profile: LiveData<Customer> get() = _profile
    private var userRepository: UserRepository? = UserRepositoryImp(RemoteDataSource())
    private val historySearchRepository: HistorySearchRepository =
        HistorySearchRepositoryImp(application)
    fun getCustomer() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository?.getCustomer()
            if (response?.isSuccessful == true) {
                _profile.postValue(response.body())
            } else {
                Log.d("getProfile", "NULLLL")
            }
        }
    }
    fun clearDatabase(){
        viewModelScope.launch(Dispatchers.IO){
            historySearchRepository.deleteAllProducts()
        }
    }
}