package com.future.mvpexample.dagger.di.components

import com.future.mvpexample.dagger.di.modules.AppModule
import com.future.mvpexample.dagger.di.modules.NetModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class])
interface AppComponent {
  fun retrofit(): Retrofit

}