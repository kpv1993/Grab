package com.example.grab.dependencies

import com.example.grab.viewmodel.MainViewmodel
import dagger.Component

@Component(modules = [MyModule::class])
interface MyComponent{
    fun inject(mainViewmodel: MainViewmodel);
}