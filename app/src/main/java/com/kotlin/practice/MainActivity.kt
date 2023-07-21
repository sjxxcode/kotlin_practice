package com.kotlin.practice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.practice.databinding.ActivityMainBinding
import com.kotlin.practice.rengwuxian.cls1.C1Activity
import com.kotlin.practice.rengwuxian.cls2.NetActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
    }

    private fun initView() {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTest1.setOnClickListener {
            startActivity(Intent(this@MainActivity, C1Activity::class.java))
        }
        binding.btnTest2.setOnClickListener {
            startActivity(Intent(this@MainActivity, NetActivity::class.java))
        }
    }
}