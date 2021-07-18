package com.examble.task1

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.examble.task1.databinding.ActivityMainBinding
import com.examble.task1.ui.*
import com.examble.task1.ui.test.Al_Baghdadia
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var compositeDisposable: CompositeDisposable
    private lateinit var _binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(requireNotNull(_binding.root))

        _binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        makeRequestUsingHTTPOK(LoadingRequest())
        _binding.request.setOnClickListener {
            foo()
        }

    }

    private fun foo() {
        compositeDisposable = CompositeDisposable()
        val observable = Observable.create<Any> { emitter ->
            emitter.onNext(makeRequestUsingHTTPOK(SuccessRequest()))
            emitter.onComplete()
        }
        observable.observeOn(AndroidSchedulers.mainThread()).subscribe({
            it
        },
            {
                makeRequestUsingHTTPOK(FailRequest())
            }).add(compositeDisposable)
    }

    private fun makeRequestUsingHTTPOK(status: Status) {
        return when (status) {
            is FailRequest -> {
                _binding.searchContainer.visibility = View.GONE
                _binding.dataContainer.visibility = View.GONE
                _binding.errorContainer.visibility = View.VISIBLE
                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_LONG).show()
            }

            is LoadingRequest -> {
                _binding.searchContainer.visibility = View.VISIBLE
                _binding.dataContainer.visibility = View.GONE
            }
            is SuccessRequest -> {
                val url =
                    "https://albaghdadiatv.com/wp-json/loadmore/category/%D8%B1%D9%8A%D8%A7%D8%B6%D8%A9/2/73514"

                val request = Request.Builder().url(url).build()
                val client = OkHttpClient()

                _binding.searchContainer.visibility = View.GONE
                _binding.dataContainer.visibility = View.VISIBLE

                client.newCall(request).enqueue(object : Callback {
                    override fun onResponse(call: Call, response: Response) {
                        val body = response.body?.string()
                        val result = GsonBuilder().create()
                        val station = result.fromJson(body, Al_Baghdadia::class.java)

                        runOnUiThread {
                            _binding.recyclerView.adapter = MainAdapter(station)
                        }
                    }

                    override fun onFailure(call: Call, e: IOException) {
                        println("Fail")
                    }

                })
            }

        }
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }

}
