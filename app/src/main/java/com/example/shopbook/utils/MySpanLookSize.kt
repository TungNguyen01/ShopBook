package com.example.shopbook.utils

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.shopbook.data.model.Category
import com.example.shopbook.ui.adapter.CategoryIndexAdapter
import kotlin.system.exitProcess

@Suppress("UNREACHABLE_CODE")
class MySpanLookSize(
    val adapter: CategoryIndexAdapter,
    val spanCount1: Int,
    val spanCount2: Int,
    val spanCount3: Int,
    val spanCount4: Int,
) :
    GridLayoutManager.SpanSizeLookup() {
    private var categoryCopyList = adapter.getAllCategory() as MutableList<Category>
    private var categoryListNew = mutableListOf<Category>()
    private var categoryList = adapter.getAllCategory()
    private var tmp = 0

    //    private var size = 30
    private var a = 0
    private var b = 35
    override fun getSpanSize(position: Int): Int {

//        val categoryList = adapter.getAllCategory()
//        categoryCopyList.clear()
//        categoryCopyList.addAll(categoryList)
//        size.let {
//            when (it) {
//            }
//        }
        val size = adapter.getCategory(position).name?.length
        if (size != null && size > 25) {
            return spanCount2
        }
        return spanCount1
//        var span = 1
//
////        val x = getSizeItemCategory(size)
//        val x = adapter.getCategory(position).name?.length
//        Log.d("SPANSIZEItem", x.toString())
//        when {
//            x in 25..30 -> span = spanCount4
//            x in 15..24 -> span = spanCount3
//            x in 7..14 -> span = spanCount2
//            x in 2..6 -> span = spanCount1
//        }
////        size -= x
//        if (size <= 0 || x == 0) {
//            size = 30
//        }
//        Log.d("SpanSizeSpan", span.toString())
//        return span
    }

    fun getSizeItemCategory(size: Int): Int {
        var sizeItem = 0
        for (category in categoryCopyList) {
            category.name?.length?.let {
                if (it < size) {
                    categoryCopyList.remove(category)
                    sizeItem = it
                    categoryListNew.add(category)
                    Log.d("CATEGORYLIST", categoryList.toString())
                    Log.d("SPANsizeCategorySize", categoryCopyList.size.toString())
                }
            }
            if (sizeItem != 0) {
                Log.d("SPANsizeCategoryItem", category.toString())
                break
            }
        }
        Log.d("LISSTT", categoryListNew.toString())
        Log.d("LISSTTSize", categoryListNew.size.toString())
        return sizeItem
    }

    fun getNewAllCategory(): MutableList<Category> = categoryListNew
}

//[Category(categoryId=1, name=Tiểu thuyết, description=),
//Category(categoryId=2, name=Truyện ngắn, description=),
//Category(categoryId=3, name=Light Novel, description=),
//Category(categoryId=4, name=Trinh thám - Kiếm hiệp, description= ),
//Category(categoryId=8, name=Du ký, description=),
//Category(categoryId=5, name=Huyền bí - Giả tưởng - Kinh dị, description=),
//Category(categoryId=6, name=Tác phẩm kinh điển, description=),
//Category(categoryId=7, name=Phóng sự - Ký sự, description=),
//Category(categoryId=9, name=12 cung hoàng đạo, description=),
//Category(categoryId=10, name=Tuổi Teen, description=),
//Category(categoryId=11, name=Hài Hước, description=)]


//[Category(categoryId=1, name=Tiểu thuyết, description=),
//Category(categoryId=2, name=Truyện ngắn, description=),
//Category(categoryId=8, name=Du ký, description=),
//Category(categoryId=3, name=Light Novel, description=),
//Category(categoryId=6, name=Tác phẩm kinh điển, description=)]