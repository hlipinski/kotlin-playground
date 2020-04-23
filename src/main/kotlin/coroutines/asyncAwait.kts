package coroutines

import kotlinx.coroutines.*
import java.util.concurrent.Executors

runBlocking {
    val count: Deferred<Int> = async(Dispatchers.Default) {
        println("Fetching processors in ${Thread.currentThread()}")
        Runtime.getRuntime().availableProcessors()
    }

    println("Called count function in ${Thread.currentThread()}")

    println("Number of cores: ${count.await()}")
}