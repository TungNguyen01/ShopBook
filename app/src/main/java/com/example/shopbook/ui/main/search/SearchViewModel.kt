package com.example.shopbook.ui.main.search

import androidx.lifecycle.ViewModel
import com.example.shopbook.data.model.Product

class SearchViewModel : ViewModel(){
    private val bookList= mutableListOf<Product>()
    init {
        bookList.add(
            Product(1, "Sach sfdsf", "Descriptisfdson", "1000VND",
                "200VND", "https://cdn0.fahasa.com/media/catalog/product/h/o/hoi-chung-tuoi-thanh-xuan_9_ban-pho-thong.jpg", "", "", 0, 0, 0))
        bookList.add(Product(3, "Sach sdfsf", "Descriptisfdson", "1000VND",
            "200VND", "https://cdn0.fahasa.com/media/catalog/product/8/9/8935280913738-dd.jpg", "", "", 0, 0, 0))
        bookList.add(Product(2, "Sach 1321", "Descriptisfdson", "1000VND",
            "200VND", "https://cdn0.fahasa.com/media/catalog/product/8/9/8935280913738-dd.jpg", "", "", 0, 0, 0))
    }
    fun getProducts():List<Product>{
        return bookList
    }
}