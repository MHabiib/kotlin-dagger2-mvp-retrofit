package com.future.mvpexample

import android.util.Log
import com.example.*
import com.future.mvpexample.dagger.MyActivity
import com.future.mvpexample.dagger.di.interfaces.BookApiInterfaces
import com.future.mvpexample.dagger.di.presenter.MainContract
import com.future.mvpexample.dagger.di.presenter.MainPresenterImpl
import com.future.mvpexample.dagger.models.getFetchBookRespose
import io.reactivex.Flowable.just
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainPresenterImplTest {

    @InjectMocks
    private lateinit var presenter: MainPresenterImpl

    @Mock
    private lateinit var myActivity: MyActivity

    @Mock
    lateinit var bookApi: BookApiInterfaces

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun onButtonClick() {
        `when`(bookApi.getBook(ArgumentMatchers
            .anyString()))
            .thenReturn(Observable.just(getFetchBookRespose()))
        presenter.onButtonClick(ArgumentMatchers.anyString())
        verify(myActivity).setBook("blibli.com")
    }
}
