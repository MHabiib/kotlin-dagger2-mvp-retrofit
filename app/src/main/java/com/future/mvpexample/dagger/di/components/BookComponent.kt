package com.future.mvpexample.dagger.di.components

import com.future.mvpexample.dagger.MyActivity
import com.future.mvpexample.dagger.di.modules.BookModule
import com.future.mvpexample.dagger.di.scope.BookScope
import dagger.Component
import retrofit2.Retrofit

@BookScope
@Component(dependencies = [AppComponent::class], modules = [BookModule::class])
interface BookComponent {
    fun inject(myActivity: MyActivity)
}