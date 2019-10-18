package com.future.mvpexample.dagger

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.BookRes
import com.future.mvpexample.R
import com.future.mvpexample.dagger.di.interfaces.BookApiInterfaces
import com.future.mvpexample.dagger.di.presenter.MainContract
import com.future.mvpexample.dagger.di.presenter.MainPresenterImpl
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

        (application as MyApp).getBookComponent()?.inject(this)
        presenter.onAttach(this)
        textView = findViewById(R.id.titleText)
        val bookInput = findViewById<TextView>(R.id.bookInput)
        val button: Button = findViewById(R.id.searchButton)

        button.setOnClickListener{
            presenter.onButtonClick(bookInput.text.toString())
        }

            val call = bookApiInterfaces.getBook("asd")

            call.enqueue(object : Callback<BookRes> {
                override fun onResponse(
                    call: Call<BookRes>,
                    response: Response<BookRes>)
                {
                    if(response.isSuccessful) {
                        Log.i("HASILNYAAA", response.body().toString())
                        Toast.makeText(this@MyActivity, "Data Retrieved",
                            Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<BookRes>, t: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            })


    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun setBook(string: String) {
        textView!!.text = string
    }

}