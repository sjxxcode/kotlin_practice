package com.kotlin.practice.coroutines.rengwuxian.cls1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kotlin.practice.databinding.ActivityC1Binding
import kotlinx.coroutines.*

class C1Activity : AppCompatActivity() {

    private val contentArray = arrayOf("123", "456", "789", "JQK", "A")

    private val binding by lazy {
        ActivityC1Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        test3()
    }

    private fun initView() {
        setContentView(binding.root)

        binding.btnTest1.setOnClickListener {
            test1()
        }

        binding.btnTest2.setOnClickListener {
            test2()
        }

        test3()
    }

    private fun test3() {
        MainScope().launch {
            // 为什么不会anr?
            delay(1000 * 20)
        }
    }

    private var job: Job? = null
    private fun test2() {
        job?.cancel()

        job = MainScope().launch {
            val content = test2IO()
            binding.textTest2.text = content
        }
    }

    private suspend fun test2IO(): String {
        return withContext(Dispatchers.IO) {
            delay(100)
            contentArray[(Math.random() * contentArray.size).toInt()]
        }
    }

    private fun test1() {
        GlobalScope.launch(Dispatchers.Main) {
            Log.e("C1Activity", "test() start...")

            testIO()
            testUI()

            testIO()
            testUI()

            Log.e("C1Activity", "test() end...")
        }
    }

    private fun testUI() {
        Log.e("C1Activity", "UI run thread:${Thread.currentThread().name}")
    }

    private suspend fun testIO() {
        withContext(Dispatchers.IO) {
            delay(1000 * 2)
            Log.e("C1Activity", "IO run thread:${Thread.currentThread().name}")
        }
    }
}