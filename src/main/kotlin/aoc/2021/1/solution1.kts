import java.io.File

var prev = 0
var counter = 0
val file = File("input.txt")
val numbers = file.readLines()
//val numbers = listOf<Int>(199,200,208,210,200,207,240,269,260,263 )
println(numbers.size)

var idx = 0
val grouped = mutableListOf<Int>();
while (idx + 2 < numbers.size) {
    grouped.add(numbers[idx].toInt() + numbers[idx+1].toInt() + numbers[idx+2].toInt())
    idx++;
}

grouped.forEach {
    val num = it
    if (prev == 0) prev = num
    else if (prev < num) counter++
    prev = num
}
println(counter)
