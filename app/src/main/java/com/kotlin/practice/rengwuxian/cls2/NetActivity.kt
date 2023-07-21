package com.kotlin.practice.rengwuxian.cls2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kotlin.practice.databinding.ActivityNetBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class NetActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityNetBinding.inflate(layoutInflater)
    }

    private val api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(Api::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        setContentView(binding.root)

        binding.btnTest1.setOnClickListener {
            //request()
            //request2()
            //request3()
            //request3_optimize()
            //request4()
            request4_opt()
        }
    }

    private fun request() {
        api.listRepos("sjxxcode").enqueue(object : Callback<List<Repo?>?> {
            override fun onResponse(call: Call<List<Repo?>?>, response: Response<List<Repo?>?>) {
                binding.textTest1.text = response.body()?.get(0)?.name
            }

            override fun onFailure(call: Call<List<Repo?>?>, t: Throwable) {
                Log.e("1111111", "onFailure()", t)
            }
        })
    }

    private fun request2() {
        MainScope().launch {
            try {
                val repos = api.listRepos2("sjxxcode")
                binding.textTest1.text = repos?.get(0)?.name
            } catch (e: HttpException) {
                Log.e("1111111", "出错了!", e)
            }
        }
    }

    private fun request3() {
        MainScope().launch {
            try {
                val startTime = System.currentTimeMillis()

                val repos1 = api.listRepos2("sjxxcode")
                var repName1 = repos1?.get(0)?.name

                val repos2 = api.listRepos2("google")
                var repName2 = repos2?.get(0)?.name

                val repos3 = api.listRepos2("rengwuxian")
                var repName3 = repos3?.get(0)?.name

                binding.textTest1.text =
                    "$repName1-->$repName2-->$repName3(耗时:${System.currentTimeMillis() - startTime}ms)"

            } catch (e: HttpException) {
                Log.e("1111111", "出错了!", e)
            }
        }
    }

    private fun request3_optimize() {
        MainScope().launch {
            try {
                val startTime = System.currentTimeMillis()

                val repos1 = async { api.listRepos2("sjxxcode") }
                val repos2 = async { api.listRepos2("google") }
                val repos3 = async { api.listRepos2("rengwuxian") }

                binding.textTest1.text =
                    "${repos1.await()[0].name}-->${repos2.await()[0].name}-->${repos3.await()[0].name}(耗时:${System.currentTimeMillis() - startTime}ms)"

            } catch (e: HttpException) {
                Log.e("1111111", "出错了!", e)
            }
        }
    }

    private fun request4() {
        MainScope().launch {
            try {
                val startTime = System.currentTimeMillis()

                val data1 = analogRequest("123")
                val data2 = analogRequest("456")
                val data3 = analogRequest("789")

                binding.textTest1.text =
                    "$data1-->$data2-->$data3}(耗时:${System.currentTimeMillis() - startTime}ms)"

            } catch (e: HttpException) {
                Log.e("1111111", "出错了!", e)
            }
        }
    }

    private suspend fun analogRequest(data: String): String {
        delay(1000)
        return data
    }

    private fun request4_opt() {
        MainScope().launch {
            try {
                val startTime = System.currentTimeMillis()

                val repos1 = async {
                    Log.e("1111111", "request4_opt() data:123 start")
                    delay(1000)
                    Log.e("1111111", "request4_opt() data:123 end")
                    "123"
                }
                val repos2 = async {
                    Log.e("1111111", "request4_opt() data:456 start")
                    delay(1000)
                    Log.e("1111111", "request4_opt() data:456 end")
                    "456"
                }
                val repos3 = async {
                    Log.e("1111111", "request4_opt() data:789 start")
                    delay(1000)
                    Log.e("1111111", "request4_opt() data:789 end")
                    "789"
                }

                binding.textTest1.text =
                    "${repos1.await()}-->${repos2.await()}-->${repos3.await()}(耗时:${System.currentTimeMillis() - startTime}ms)"

            } catch (e: HttpException) {
                Log.e("1111111", "出错了!", e)
            }
        }
    }
}