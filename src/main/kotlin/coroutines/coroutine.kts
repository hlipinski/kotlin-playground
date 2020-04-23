package coroutines

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun task(number: Int) {
    println("Started task $number in ${Thread.currentThread()}")
    println("Stopped task $number in ${Thread.currentThread()}")
}

println("Start")

runBlocking {
    launch { task(1) }
    launch { task(2) }

    println("Called tasks 1 and 2 from ${Thread.currentThread()}")
}

println("Done")