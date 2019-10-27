package com.example.grab.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
class Article {
    @Id
    var id: Long = 0
    @SerializedName("author")
    @Expose
    var author: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("urlToImage")
    @Expose
    var urlToImage: String? = null
    @SerializedName("publishedAt")
    @Expose
    var publishedAt: String? = null
    @SerializedName("content")
    @Expose
    var content: String? = null

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}