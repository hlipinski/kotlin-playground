package aoc.`2021`.`7`

import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {
    println("Part 1: ${Solution("input.txt").solve()}")
    println("Part 2: ${Solution2("input.txt").solve()}")
}

class Solution2(fileName: String): Solution(fileName) {
    override fun calculateFuel(alignTo: Int): Int {
        return crabs.map { abs(it - alignTo) }.map { it.sumIncreasing() }.sum()
    }

    fun Int.sumIncreasing(): Int {
        return IntRange(1, this).sum()
    }
}

open class Solution(fileName: String) {

    private val file = File("aoc/2021/7", fileName)
    val crabs = convertFile()

    fun solve(): Int {
        var solution = Int.MIN_VALUE
        IntRange(crabs.min()!!, crabs.max()!!).forEach {
            val fuel = calculateFuel(it)
            if (solution == Int.MIN_VALUE) solution = fuel
            else if (fuel < solution) solution = fuel
        }
        return solution
    }

    fun convertFile(): List<Int> {
        return file.readLines()[0].split(",").map { it.toInt() }
    }

    open fun calculateFuel(alignTo: Int): Int {
        return crabs.map { abs(it - alignTo) }.sum()
    }
}
