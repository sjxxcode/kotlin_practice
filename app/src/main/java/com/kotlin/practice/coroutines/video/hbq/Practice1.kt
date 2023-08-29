package com.kotlin.practice.coroutines.video.hbq

/**
 * kotlin协程的基本要素
 */
object Practice1 {

    @JvmStatic
    fun main(args: Array<String>) {
        // 挂起函数只能在协程或其他挂起函数中调用
        //test1()
    }

    private suspend fun test1() {
        test2()
    }

    private suspend fun test2() {

    }
}