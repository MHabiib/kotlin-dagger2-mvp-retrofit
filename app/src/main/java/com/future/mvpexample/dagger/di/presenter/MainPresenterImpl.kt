package com.future.mvpexample.dagger.di.presenter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.example.BookRes
import com.future.mvpexample.dagger.MyActivity
import com.future.mvpexample.dagger.di.interfaces.BookApiInterfaces
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.await
import javax.inject.Inject

class MainPresenterImpl @Inject constructor(): MainContract.Presenter{

    @Inject
    lateinit var bookApi: BookApiInterfaces
    var returnValue: String = ""
    private val viewModelJob = Job()
    private var subscription: Disposable? = null
   // private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )
    var mainView: MainContract.View? = null

    fun onAttach (mainView: MainContract.View){
        this.mainView = mainView
    }

    override fun onButtonClick(input: String) {

        subscription = bookApi
            .getBook(input)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { Log.i("RX ","On Terminate")}
            .subscribe(
                { listResult-> onFinished(listResult
                    .items?.get(0)?.volumeInfo?.title.toString())},
                { Log.i("RX ","ERRORR")}
            )

      /*  coroutineScope.launch {
            val getPropertiesDeferred = bookApi.getBook(input)
            try {
                val listResult = getPropertiesDeferred.await()
                returnValue = listResult.items?.get(0)?.volumeInfo?.title.toString()
                Log.i("ReturnValue",returnValue)
                onFinished(returnValue)
            } catch (e: Exception) {
                returnValue = "Failure: ${e.message}"
            }
        }*/
    }

    override fun onDestroy() {
        mainView = null
        subscription?.dispose()
    }

    override fun onFinished(string: String) {
        if (mainView != null) {
            mainView!!.setBook(string)
        }
    }


}