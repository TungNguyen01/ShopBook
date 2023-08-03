package com.example.shopbook.ui.category.categoryindex

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopbook.data.model.Category
import com.example.shopbook.data.repository.category.CategoryRepository
import com.example.shopbook.data.repository.category.CategoryRepositoryImp
import com.example.shopbook.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryIndexViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _categoryList = MutableLiveData<List<Category>>()
    val categoryList: LiveData<List<Category>> get() = _categoryList
    private var categotyRepository: CategoryRepository? = CategoryRepositoryImp(RemoteDataSource())
    fun getAllCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = categotyRepository?.getCategory()
            if (response?.isSuccessful == true) {
                _categoryList.postValue(response.body()?.categories)
            } else {
                Log.d("CategoryNULL", "NULL")
            }
        }
    }
}