package com.example.grab

import android.app.Application
import com.example.grab.db.ObjectBox
import com.example.grab.dependencies.DaggerMyComponent
import com.example.grab.dependencies.MyComponent
import com.example.grab.dependencies.MyModule

class AppController : Application() {

    private var myEngineComponent: MyComponent? = null
    private var myDbHelperComponent: MyComponent? = null

    override fun onCreate() {
        super.onCreate()
        myEngineComponent = createComponent()
        myDbHelperComponent = createComponent()
        ObjectBox.init(this)
    }

    internal fun getMyEngineComponent(): MyComponent? {
        return myEngineComponent
    }

    internal fun getMyDbHelperComponent(): MyComponent? {
        return myDbHelperComponent
    }

    companion object{
        fun createComponent(): MyComponent {
            return DaggerMyComponent
                .builder()
                .myModule(MyModule())
                .build()
        }
    }

}