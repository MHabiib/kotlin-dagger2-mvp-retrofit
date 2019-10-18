package com.future.mvpexample.dagger.di.modules

import com.future.mvpexample.dagger.di.interfaces.BookApiInterfaces
import com.future.mvpexample.dagger.di.scope.BookScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class BookModule {

    @Provides
    @BookScope
    fun provideBookInterface(retrofit: Retrofit): BookApiInterfaces {
        return retrofit.create(BookApiInterfaces::class.java)
    }
}