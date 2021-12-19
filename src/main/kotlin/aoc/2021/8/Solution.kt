package aoc.`2021`.`8`

import java.io.File

fun main(args: Array<String>) {
    println("Part 1: ${Solution("input.txt").solve()}")
    println("Part 2: ${Solution2("input.txt").solve()}")
}

class Solution2(fileName: String) : Solution(fileName) {
    override fun solve(): Int {
        val decoded = convertFile()
        return decoded.map { decode(it.first, it.second) }.sum()
    }

    private fun convertFile(): List<Pair<List<String>, List<String>>> {
        return file.readLines().map { parseLineWithDictionary(it) }
    }

    fun parseLineWithDictionary(line: String): Pair<List<String>, List<String>> {
        val pairs = line.split(" | ")
        return Pair(pairs[0].split(" "), pairs[1].split(" "))
    }

    fun decode(dictionary: List<String>, code: List<String>): Int {
        val one = dictionary.filter { it.length == 2 }[0]
        val four = dictionary.filter { it.length == 4 }[0]
        val seven = dictionary.filter { it.length == 3 }
        val eight = dictionary.filter { it.length == 7 }
        return code.map { mapToNumber(it, one, four) }.reduce { acc, i -> acc + i }.toInt()
    }

    fun mapToNumber(number: String, one: String, four: String): String =
            when {
                number.length == 2 -> "1"
                number.length == 3 -> "7"
                number.length == 4 -> "4"
                number.length == 7 -> "8"
                number.length == 5 && (number.toList() - one.toList()).size == 3 -> "3"
                number.length == 5 && (number.toList() - four.toList()).size == 2 -> "5"
                number.length == 5 && (number.toList() - four.toList()).size == 3 -> "2"
                number.length == 6 && (number.toList() - one.toList()).size == 5 -> "6"
                number.length == 6 && (number.toList() - four.toList()).size == 2 -> "9"
                number.length == 6 && (number.toList() - four.toList()).size == 3 -> "0"
                else -> {
                    println("number: $number, one: $one, four: $four")
                    "?"
                }
            }
}

open class Solution(fileName: String) {

    val file = File("aoc/2021/8", fileName)

    open fun solve(): Int {
        val validLengths = listOf(2, 3, 4, 7)
        return convertFile().count { validLengths.contains(it.length) }
    }

    private fun convertFile(): List<String> {
        return file.readLines().flatMap { parseLine(it) }
    }

    fun parseLine(line: String): List<String> {
        val pairs = line.split(" | ")
        return pairs[1].split(" ")
    }
}
