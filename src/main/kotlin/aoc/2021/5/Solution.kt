package aoc.`2021`.`5`

import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    println("Part 1: ${Solution1("input.txt").solve()}")
    println("Part 2: ${Solution2("input.txt").solve()}")
}

class Solution2(fileName: String) : Solution(fileName) {

    override fun solve(): Int {
        val map = convertFile(file.readLines())
        return map.values.filter { it > 1 }.count()
    }

    fun convertFile(lines: List<String>): HashMap<String, Int> {
        val map = hashMapOf<String, Int>()
        lines.forEach { line ->
            val (start, end) = parseLine(line)

            val max0 = max(start[0], end[0])
            val max1 = max(start[1], end[1])
            val min0 = min(start[0], end[0])
            val min1 = min(start[1], end[1])

            if (start[0] == end[0]) {
                IntRange(min1, max1).forEach {
                    val idx = "${start[0]}|$it"
                    map[idx] = map.getOrDefault(idx, 0) + 1
                }
            } else if (start[1] == end[1]) {
                IntRange(min0, max0).forEach {
                    val idx = "$it|${start[1]}"
                    map[idx] = map.getOrDefault(idx, 0) + 1
                }
            } else if (abs(end[0] - start[0]) == abs(end[1] - start[1])) {
                if (end[0] - start[0] > 0 && end[1] - start[1] > 0) {
                    var counter = 0
                    while (start[0] + counter <= end[0]) {
                        val idx = "${start[0] + counter}|${start[1] + counter}"
                        map[idx] = map.getOrDefault(idx, 0) + 1
                        counter++
                    }
                } else if (end[0] - start[0] > 0 && end[1] - start[1] < 0) {
                    var counter = 0
                    while (start[0] + counter <= end[0]) {
                        val idx = "${start[0] + counter}|${start[1] - counter}"
                        map[idx] = map.getOrDefault(idx, 0) + 1
                        counter++
                    }
                } else if (end[0] - start[0] < 0 && end[1] - start[1] < 0) {
                    var counter = 0
                    while (end[0] + counter <= start[0]) {
                        val idx = "${end[0] + counter}|${end[1] + counter}"
                        map[idx] = map.getOrDefault(idx, 0) + 1
                        counter++
                    }
                } else if (end[0] - start[0] < 0 && end[1] - start[1] > 0) {
                    var counter = 0
                    while (end[0] + counter <= start[0]) {
                        val idx = "${end[0] + counter}|${end[1] - counter}"
                        map[idx] = map.getOrDefault(idx, 0) + 1
                        counter++
                    }
                }
            }
        }
        return map
    }
}

class Solution1(fileName: String) : Solution(fileName) {

    override fun solve(): Int {
        val map = convertFile()
        return map.values.filter { it > 1 }.count()
    }

    fun convertFile(): HashMap<String, Int> {
        val map = hashMapOf<String, Int>()
        file.forEachLine { line ->
            val (start, end) = parseLine(line)

            if (start[0] == end[0]) {
                IntRange(min(start[1], end[1]), max(start[1], end[1])).forEach {
                    val idx = "${start[0]}|$it"
                    map[idx] = map.getOrDefault(idx, 0) + 1
                }
            } else if (start[1] == end[1]) {
                IntRange(min(start[0], end[0]), max(start[0], end[0])).forEach {
                    val idx = "$it|${start[1]}"
                    map[idx] = map.getOrDefault(idx, 0) + 1
                }
            }
        }
        return map
    }
}

sealed class Solution(fileName: String) {

    val file = File("aoc/2021/5", fileName)

    open fun solve(): Int {
        return -1
    }

    fun parseLine(line: String): Pair<List<Int>, List<Int>> {
        val pairs = line.split(" -> ")
        val start = pairs[0].split(",").map { it.toInt() }
        val end = pairs[1].split(",").map { it.toInt() }
        return Pair(start, end)
    }
}
