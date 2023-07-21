package com.kotlin.practice.coroutines.rengwuxian.cls1

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object c1 {

    @JvmStatic
    fun main(args: Array<String>) {
        GlobalScope.launch {
            funIO()
            funUI()
        }
    }

    private fun funUI() {
        println("UI run thread:${Thread.currentThread().name}")
    }

    private fun funIO() {
        println("IO run thread:${Thread.currentThread().name}")
    }
}