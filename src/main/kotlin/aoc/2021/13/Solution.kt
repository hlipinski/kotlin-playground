package aoc.`2021`.`13`

import java.io.File
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

fun main(args: Array<String>) {
    println("Part 1: ${Solution("input.txt").solve()}")
    println("Part 2: ${Solution2("input.txt").solve()}")
}

class Solution2(fileName: String): Solution(fileName) {
    override fun solve(): Int {
        var newDots = dots
        folds.forEach {
            newDots = fold(newDots, it)
        }
        IntRange(0, 5).forEach { num ->
            var row = MutableList(40) { "." }
            newDots.filter { it.contains("|$num") }.forEach { row[it.split("|").map { it.toInt() }[0]] = "#" }
            println(row)
        }
        return -1
    }
}

open class Solution(fileName: String) {

    val file = File("aoc/2021/13", fileName)
    val dots = convertFileToDots()
    val folds = convertFileToFolds()

    open fun solve(): Int {
        return fold(dots, folds[0]).count()
    }

    fun fold(dots: HashSet<String>, line: String): HashSet<String> =
            if (line.startsWith("x=")) foldX(dots, line.substringAfter("x=").toInt())
            else foldY(dots, line.substringAfter("y=").toInt())

    fun foldX(dots: HashSet<String>, xLine: Int): HashSet<String> {
        val newDots = hashSetOf<String>()
        dots.forEach {
            val pos = it.split("|").map { it.toInt() }
            if (pos[0] < xLine) newDots.add(it)
            else {
                val delta = pos[0] - xLine
                newDots.add("${xLine - delta}|${pos[1]}")
            }
        }
        return newDots
    }

    fun foldY(dots: HashSet<String>, yLine: Int): HashSet<String> {
        val newDots = hashSetOf<String>()
        dots.forEach {
            val pos = it.split("|").map { it.toInt() }
            if (pos[1] < yLine) newDots.add(it)
            else {
                val delta = pos[1] - yLine
                newDots.add("${pos[0]}|${yLine - delta}")
            }
        }
        return newDots
    }

    fun convertFileToDots(): HashSet<String> {
        val resultSet = hashSetOf<String>()
        file.readLines()
                .filter { it != "" }
                .filter { !it.startsWith("fold") }
                .forEach {
                    val pos = it.split(",")
                    resultSet.add("${pos[0]}|${pos[1]}")
                }
        return resultSet
    }

    fun convertFileToFolds(): List<String> {
        val resultList = file.readLines()
                .filter { it.startsWith("fold") }
                .map { it.substringAfter("fold along ") }
        return resultList
    }
}
