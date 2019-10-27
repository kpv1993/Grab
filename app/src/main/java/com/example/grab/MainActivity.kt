package com.example.grab

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import com.example.grab.pojo.Article
import com.example.grab.viewmodel.MainViewmodel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NewsAdapter.ItemClickListener {

    private lateinit var mTopToolbar: Toolbar
    private lateinit var viewModel: MainViewmodel
    var list: ArrayList<Article> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTopToolbar = findViewById<Toolbar>(R.id.my_toolbar)
        mTopToolbar.title = ""
        setSupportActionBar(mTopToolbar)

        //instantiating viewmodel and adding it to the lifecycle
        viewModel = ViewModelProviders.of(this).get(MainViewmodel::class.java)
        lifecycle.addObserver(viewModel)

        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
        rView.layoutManager = linearLayoutManager
        val newsAdapter: NewsAdapter = NewsAdapter(list, this)
        rView.adapter = newsAdapter

        viewModel.getSuccessObservable().observe(this, Observer {
            rViewProgress.visibility = View.GONE
            successView.visibility = View.VISIBLE
            retryView.visibility = View.GONE
            list.clear()
            list.addAll(it!!)
            newsAdapter.notifyDataSetChanged()
        })

        viewModel.getFailureObservable().observe(this, Observer {
            rViewProgress.visibility = View.GONE
            successView.visibility = View.GONE
            retryView.visibility = View.VISIBLE
        })

        viewModel.getDBObservable().observe(this, Observer {
            rViewProgress.visibility = View.GONE
            successView.visibility = View.VISIBLE
            retryView.visibility = View.GONE
            list.clear()
            list.addAll(it!!)
            newsAdapter.notifyDataSetChanged()
        })

        retryView.setOnClickListener(View.OnClickListener {
            sendRequest()
        })

        sendRequest()
        newsAdapter.setClickListener(this)
    }

    private fun sendRequest(){
        rViewProgress.visibility = View.VISIBLE
        viewModel.sendQuery("us",resources.getString(R.string.apiKey))
    }

    override fun onClick(aView: View, pos: Int) {
        val i = Intent(this@MainActivity, WebViewActivity::class.java)
        i.putExtra("url", list[pos].url)
        startActivity(i)
    }
}
