package aoc.`2021`.`4`

import java.io.File

fun main(args: Array<String>) {
    println("Part 1: ${Solution1("input.txt").solve()}")
    println("Part 2: ${Solution2("input.txt").solve()}")
}

class Solution1(fileName: String): Solution(fileName) {

    override fun solve(): Int {
        numbers.forEach { number ->
            matrixes.forEach { matrix ->
                val result = matrix.checkNumber(number)
                if (result > -1) return result * matrix.sumAll()
            }
        }

        return -1
    }
}

class Solution2(fileName: String): Solution(fileName) {

    override fun solve(): Int {
        var wins = 0
        numbers.forEach { number ->
            matrixes.forEach { matrix ->
                val result = matrix.checkNumber(number)
                if (result > -1) {
                    wins++
                }
                if (wins == matrixes.size) return result * matrix.sumAll()
            }
        }

        return -1
    }
}

sealed class Solution(fileName: String) {

    val file = File("aoc/2021/4", fileName)
    val numbers = file.readLines()[0].split(",").map { it.toInt() }
    val matrixes = uploadFile()

    open fun solve(): Int {
        return -1
    }

    fun uploadFile(): List<BingoMatrix> {
        val result = mutableListOf(BingoMatrix())

        val lines = file.readLines().drop(2)
        lines.forEach {
            if (it == "") {
                result.add(BingoMatrix())
            } else {
                val row = it.split(" ").filter { it != "" }.map { it.toInt() }
                result[result.lastIndex].rows.add(row.toHashSet())
                row.forEachIndexed { index, i ->
                    result[result.lastIndex].columns[index].add(i)
                }
            }
        }
        return result
    }
}

class BingoMatrix {
    val rows = hashSetOf<HashSet<Int>>()
    val columns = listOf<HashSet<Int>>(HashSet(), HashSet(), HashSet(), HashSet(), HashSet())
    var valid = true

    fun checkNumber(number: Int): Int {
        if (!valid) return -1
        rows.forEach {
            it.remove(number)
            if (it.isEmpty()) {
                valid = false
                return number
            }
        }
        columns.forEach {
            it.remove(number)
            if (it.isEmpty()) {
                valid = false
                return number
            }
        }
        return -1
    }

    fun sumAll(): Int = rows.map { row -> row.sum() }.sum()
}
