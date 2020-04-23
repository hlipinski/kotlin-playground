package coroutines

fun primes(start: Int): Sequence<Int> =
    sequence {
        println("Starting to look")
        var index = start
        while (true) {
            if (index > 1 && (2 until index).none {
                    i -> index % i == 0
                }) {
                yield(index)
                println("Generating next after $index")
            }
            index++
        }
    }

primes(0)
    .take(30)
    .forEach { prime -> println("Received prime $prime") }