package com.example.shopbook.ui.profile.updateprofile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopbook.data.model.Customer
import com.example.shopbook.data.repository.user.UserRepository
import com.example.shopbook.data.repository.user.UserRepositoryImp
import com.example.shopbook.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class UpdateProfileViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _profile = MutableLiveData<Customer>()
    val profile: LiveData<Customer> get() = _profile
    private var userRepository: UserRepository? = UserRepositoryImp(RemoteDataSource())
    fun getCustomer() {

        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository?.getCustomer()
            if (response?.isSuccessful == true) {
                _profile.postValue(response.body())
            } else {
                Log.d("NNULLL", "NULLLL")
            }
        }
    }
    fun updateCustomer(name:String, address:String, dob:String, gender:String, mob_phone:String){
        viewModelScope.launch(Dispatchers.IO){
            val response=userRepository?.updateCustomer(name, address, dob, gender, mob_phone)
            if(response?.isSuccessful==true){
                _profile.postValue(response.body())
            }else{
                Log.d("FAIL","FAIL")
            }
        }
    }

    fun changeAvatar(image: MultipartBody.Part) {
        viewModelScope.launch(Dispatchers.IO) {
            val response=userRepository?.changeAvatar(image)
            if(response?.isSuccessful==true){
                _profile.postValue(response.body())
                Log.d("OKKKKK", "OKKKK")
            }else{
                Log.d("FAILLLL", "FAILLL")
            }
        }
    }
}