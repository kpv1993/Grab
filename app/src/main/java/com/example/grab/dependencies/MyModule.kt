package com.example.grab.dependencies

import com.example.grab.db.DbHelper
import com.example.grab.engine.Engine
import dagger.Module
import dagger.Provides

@Module
class MyModule {

    @Provides
    fun  provideMyEngine(): Engine{
        return Engine()
    }
    @Provides
    fun  provideMyDbHelper(): DbHelper{
        return DbHelper()
    }
}