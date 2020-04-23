package coroutines

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import java.util.concurrent.Executors

suspend fun task(number: Int) {
    println("Started task $number in ${Thread.currentThread()}")
    yield()
    println("Stopped task $number in ${Thread.currentThread()}")
}

Executors.newSingleThreadExecutor().asCoroutineDispatcher().use { context ->

    println("Start")

    runBlocking {
        launch(context) { task(1) }
        launch { task(2) }

        println("Called tasks 1 and 2 from ${Thread.currentThread()}")
    }

    println("Done")

}