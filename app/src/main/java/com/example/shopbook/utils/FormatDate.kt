package com.example.shopbook.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FormatDate {
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(str: String?): String {
        val str_new = LocalDate.parse(str?.substring(0, 10))
        return str_new.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }
}