package com.example.shopbook.ui.profile.changepass

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopbook.data.repository.user.UserRepository
import com.example.shopbook.data.repository.user.UserRepositoryImp
import com.example.shopbook.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChangePassViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _message=MutableLiveData<String>()
    val message:MutableLiveData<String> get() = _message
    private var userRepository: UserRepository? = UserRepositoryImp(RemoteDataSource())
    fun changePassword(email:String, old_password:String, new_password:String){
        viewModelScope.launch(Dispatchers.IO){
            val response=userRepository?.changePassword(email, old_password, new_password)
            if(response?.isSuccessful==true){
                message.postValue("UPDATE PASSWORD SUCCESSFUL")
            }else{
                message.postValue("PASSWORD IS INVALID.")
            }
        }
    }
}