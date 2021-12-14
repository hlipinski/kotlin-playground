import java.io.File

var aim = 0
var depth = 0
var position = 0
val file = File("input.txt")
val directions = file.readLines()
println(directions.size)

directions.forEach {
    val direction = it.split(" ")
    if (direction[0] == "forward") {
        position += direction[1].toInt()
        depth += direction[1].toInt() * aim
    }
    if (direction[0] == "up") aim -= direction[1].toInt()
    if (direction[0] == "down") aim += direction[1].toInt()
}

println("position $position")
println("aim $aim")
println("depth $depth")
println(position * depth)
