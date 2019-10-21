package com.future.mvpexample.dagger

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.BookRes
import com.future.mvpexample.R
import com.future.mvpexample.dagger.di.interfaces.BookApiInterfaces
import com.future.mvpexample.dagger.di.presenter.MainContract
import com.future.mvpexample.dagger.di.presenter.MainPresenterImpl
import kotlinx.android.synthetic.main.activity_book.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MyActivity : Activity(),MainContract.View {
    @Inject
    lateinit var bookApiInterfaces: BookApiInterfaces
    @Inject
    lateinit var presenter: MainPresenterImpl
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        recycler_view.layoutManager = LinearLayoutManager(this)
        (application as MyApp).getBookComponent()?.inject(this)

        presenter.onAttach(this)
        textView = findViewById(R.id.txtPostTitle)
        val bookInput = findViewById<TextView>(R.id.bookInput)
        val button: Button = findViewById(R.id.searchButton)

     //   recycler_view.adapter = ItemAdapter(this)

            button.setOnClickListener{
            presenter.onButtonClick(bookInput.text.toString())
        }



    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun setBook(string: String) {
        textView!!.text = string
       // recycler_view.adapter = string
    }


}