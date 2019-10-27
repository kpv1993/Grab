package com.example.grab.viewmodel

import android.arch.lifecycle.*
import android.content.Context
import android.provider.Settings
import android.util.Log
import com.example.grab.AppController
import com.example.grab.engine.Engine
import com.example.grab.threading.BusinessExecutor
import com.example.grab.bus.RxBus
import com.example.grab.dependencies.DaggerMyComponent
import com.example.grab.dependencies.MyModule
import com.example.grab.pojo.Article
import com.example.grab.utils.CommonUtils
import io.reactivex.disposables.Disposable
import javax.inject.Inject
import com.example.grab.MainActivity
import com.example.grab.db.DbHelper

class MainViewmodel: ViewModel(), LifecycleObserver {

    init {
        AppController.createComponent().inject(this)
    }

    @Inject lateinit var myEngine: Engine
    @Inject lateinit var myDbHelper: DbHelper
    private var TAG: String = "MainViewmodel"
    private var businessExecutor = BusinessExecutor.getInstance()
    private val getSuccessObservable = MutableLiveData<ArrayList<Article>>()
    private val getFailureObservable = MutableLiveData<String>()
    private val getDBObservable = MutableLiveData<ArrayList<Article>>()
    private lateinit var successDisposable: Disposable
    private lateinit var failureDisposable: Disposable
    private var list: ArrayList<Article> = ArrayList()

    fun getSuccessObservable(): LiveData<ArrayList<Article>> {
        return getSuccessObservable
    }

    fun getDBObservable(): LiveData<ArrayList<Article>> {
        return getDBObservable
    }

    fun getFailureObservable(): LiveData<String> {
        return getFailureObservable
    }

    fun sendQuery(country: String, apiKey: String) {
        Log.e(TAG, "sendQuery")
        businessExecutor.executeInBusinessThread(Runnable { myEngine.firstRequest(country, apiKey) })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Log.e(TAG, "onStart")
        successDisposable = RxBus.listen(ArrayList<Article>()::class.java).subscribe {
            Log.e("disposable", it.toString())
            list.clear()
            list.addAll(it)
            getSuccessObservable.postValue(list)
            businessExecutor.executeInResourceThread(Runnable {
//                if(list != dbHelper.getDataFromDB() as java.util.ArrayList<Article>?){
                myDbHelper.removeData()
                    for(item in list) {
                        myDbHelper.setDataToDB(item)
                    }
//                }
            })
        }

        failureDisposable = RxBus.listen(String::class.java).subscribe{
            if(myDbHelper.getDataFromDB() != null){
                getDBObservable.postValue(myDbHelper.getDataFromDB() as java.util.ArrayList<Article>?)
            } else {
                getFailureObservable.postValue(it)
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        successDisposable.dispose()
        failureDisposable.dispose()
    }
}