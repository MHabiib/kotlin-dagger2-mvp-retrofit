package com.future.mvpexample.dagger.di.interfaces

import com.example.BookRes
import retrofit2.Call

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface BookApiInterfaces {
    @GET("volumes")
    fun getBook(@Query("q") bookName: String): Observable<BookRes>


}
