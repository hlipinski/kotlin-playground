package coroutines

fun task(number: Int) {
    println("Started task $number in ${Thread.currentThread()}")
    println("Stopped task $number in ${Thread.currentThread()}")
}

println("Start")

run {
    task(1)
    task(2)

    println("Called tasks 1 and 2 from ${Thread.currentThread()}")
}

println("Done")