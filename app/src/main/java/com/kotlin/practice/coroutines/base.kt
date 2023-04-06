package com.kotlin.practice.coroutines

import kotlinx.coroutines.*

object base {

    @JvmStatic
    fun main(args: Array<String>) {
         test1()
        // test2()
        // test3()
        // test4()
        // test5()
        //test6()
    }

    private fun test1() {
        GlobalScope.launch { // 在后台启动一个新的协程并继续
            delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
            println("World!") // 在延迟后打印输出
        }
        println("Hello,") // 协程已在等待时主线程还在继续
        Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
    }

    private fun test2() {
        GlobalScope.launch { // 在后台启动一个新的协程并继续
            delay(1000L)
            println("World!")
        }
        println("Hello,") // 主线程中的代码会立即执行
        runBlocking {     // 但是这个表达式阻塞了主线程
            delay(2000L)  // ……我们延迟 2 秒来保证 JVM 的存活
        }
    }

    private fun test3() {
        runBlocking {
            val job = GlobalScope.launch { // 启动一个新协程并保持对这个作业的引用
                delay(1000L)
                println("World!")
            }
            println("Hello,")
            job.join() // 等待直到子协程执行结束
        }
    }

    private fun test4() {
        runBlocking {
            launch { // 在 runBlocking 作用域中启动一个新协程
                delay(1000L)
                println("World!")
            }
            println("Hello,")
        }
    }

    private fun test5() {
        runBlocking {
            launch {
                delay(200L)
                println("Task from runBlocking")
            }

            coroutineScope { // 创建一个协程作用域
                launch {
                    delay(500L)
                    println("Task from nested launch")
                }

                delay(100L)
                println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
            }

            println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
        }
    }

    private fun test6() {
        runBlocking {
            repeat(10) {
                delay(1000L)
                println(this)
            }
        }
    }
}