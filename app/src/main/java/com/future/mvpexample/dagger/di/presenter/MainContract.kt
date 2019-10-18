package com.future.mvpexample.dagger.di.presenter


interface MainContract {
    interface View {
        fun setBook(string: String)

    }

    interface Presenter {
        fun onButtonClick(input: String)

        fun onDestroy()

        fun onFinished(string: String)
    }
}