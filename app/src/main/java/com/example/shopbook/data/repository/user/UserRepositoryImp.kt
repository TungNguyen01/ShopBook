package com.example.shopbook.data.repository.user

import com.example.shopbook.data.model.Customer
import com.example.shopbook.datasource.IDataSource
import okhttp3.MultipartBody
import retrofit2.Response

class UserRepositoryImp(private val dataSource: IDataSource) : UserRepository {
    override suspend fun getCustomer(): Response<Customer>? {
        return dataSource.getCustomer()
    }

    override suspend fun updateCustomer(name:String, address:String, dob:String, gender:String, mob_phone:String): Response<Customer>? {
        return dataSource.updateCustomer(name, address, dob, gender, mob_phone)
    }

    override suspend fun updateInformation(
        name: String,
        address: String,
        mob_phone: String
    ): Response<Customer>? {
        return dataSource.updateInformation(name, address, mob_phone)
    }

    override suspend fun changePassword(
        email: String,
        old_password: String,
        new_password: String
    ): Response<Customer>? {
        return dataSource.changePassword(email, old_password, new_password)
    }

    override suspend fun changeAvatar(image: MultipartBody.Part): Response<Customer>? {
        return dataSource.changeAvatar(image)
    }
}