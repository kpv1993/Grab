package com.example.grab.db

import android.content.Context
import com.example.grab.pojo.Article
import io.objectbox.Box
import io.objectbox.kotlin.boxFor

class DbHelper{

    companion object {
        private var newsBox: Box<Article>? = ObjectBox.get().boxFor(Article::class)
        fun getInstance() : DbHelper {
            return DbHelper()
        }
    }

    fun setDataToDB(myData: Article){
        newsBox?.put(myData)
    }

    fun getDataFromDB(): MutableList<Article>? {
        if(newsBox?.all?.size == 0){
            return null
        }
        return newsBox?.all
    }

    fun removeData(){
        newsBox?.removeAll()
    }

}