package com.example.grab.utils

import android.content.Context
import java.text.SimpleDateFormat

class CommonUtils{

    companion object{

        fun getDate(s:String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            val outputFormat = SimpleDateFormat("dd-MM-yyyy")
            val date = inputFormat.parse(s)
            return outputFormat.format(date)
        }
    }
}