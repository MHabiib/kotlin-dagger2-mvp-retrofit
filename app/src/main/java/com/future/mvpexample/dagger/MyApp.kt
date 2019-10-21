package com.future.mvpexample.dagger

import android.app.Application
import com.future.mvpexample.dagger.di.components.AppComponent
import com.future.mvpexample.dagger.di.components.BookComponent
import com.future.mvpexample.dagger.di.components.DaggerAppComponent
import com.future.mvpexample.dagger.di.components.DaggerBookComponent
import com.future.mvpexample.dagger.di.modules.AppModule
import com.future.mvpexample.dagger.di.modules.BookModule
import com.future.mvpexample.dagger.di.modules.NetModule

class MyApp : Application() {


    var mAppComponent: AppComponent? = null
    var mBookComponent: BookComponent? = null

    override fun onCreate() {
        super.onCreate()

        mAppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .netModule(NetModule("https://www.googleapis.com/books/v1/"))
            .build()

        mBookComponent = DaggerBookComponent.builder()
            .appComponent(mAppComponent)
            .bookModule(BookModule())
            .build()
    }

    fun getBookComponent(): BookComponent? {
        return mBookComponent
    }


}