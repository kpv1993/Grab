package com.example.grab

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.grab.pojo.Article
import com.example.grab.utils.CommonUtils
import kotlinx.android.synthetic.main.list_item.view.*

class NewsAdapter(private val items : ArrayList<Article>?,private val context: Context) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private lateinit var clickListener: ItemClickListener
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, p0, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article: Article = items?.get(position)!!

        holder.author.text = article.author
        holder.time.text = article.publishedAt?.let { CommonUtils.getDate(it) }
        holder.title.text = article.title
        Glide.with(context).load(article.urlToImage).into(holder.imageName);
    }

    override fun getItemCount(): Int {
        return if (items != null) {
            this.items.size
        } else {
            0
        }
    }

    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val author: TextView = view.author
        val time: TextView = view.time
        val imageName: ImageView = view.image
        val title: TextView = view.title

        override fun onClick(v: View) {
            clickListener.onClick(v, adapterPosition)
        }
        init {
            view.setOnClickListener(this)
        }
    }

    fun setClickListener(itemClickListener: ItemClickListener) {
        this.clickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onClick(aView: View, pos: Int)
    }
}
