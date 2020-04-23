package coroutines

import kotlinx.coroutines.*
import java.util.concurrent.Executors

suspend fun task(number: Int) {
    println("Started task $number in ${Thread.currentThread()}")
    yield()
    println("Stopped task $number in ${Thread.currentThread()}")
}

Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
    .asCoroutineDispatcher().use { context ->

    println("Start")

    runBlocking {
        @OptIn(ExperimentalCoroutinesApi::class)
        launch(context, CoroutineStart.UNDISPATCHED) { task(1) }
        launch { task(2) }

        println("Called tasks 1 and 2 from ${Thread.currentThread()}")
    }

    println("Done")

}