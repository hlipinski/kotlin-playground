package aoc.`2021`.`10`

import java.io.File
import java.util.*

fun main(args: Array<String>) {
    println("Part 1: ${Solution("input.txt").solve()}")
    println("Part 2: ${Solution2("input.txt").solve()}")
}

class Solution2(fileName: String): Solution(fileName) {

    private val scoreMap = hashMapOf(Pair(")", 1), Pair("]", 2), Pair("}", 3), Pair(">", 4))

    override fun solve(): Long {
        val sorted = lines.map { findMissingChars(it) }.filter { it != "?" }.map { calculateScoreForLine(it) }.sorted()
        return sorted[sorted.size / 2]
    }

    fun calculateScoreForLine(line: String): Long {
        var score = 0L
        line.forEach { score = score * 5 + scoreMap[it.toString()]!! }
        return score
    }

    fun findMissingChars(line: String): String {
        val charsList = line.toList().map { it.toString() }
        val list = LinkedList<String>()
        charsList.forEach {
            if (listOf("[", "(", "{", "<").contains(it)) list.add(it)
            else if (it == "]" && list.last == "[") list.removeLast()
            else if (it == ")" && list.last == "(") list.removeLast()
            else if (it == "}" && list.last == "{") list.removeLast()
            else if (it == ">" && list.last == "<") list.removeLast()
            else return "?"
        }
        return list.map { getMissingChar(it) }.reversed().joinToString("")
    }

    fun getMissingChar(char: String): String {
        if (char == "[") return "]"
        else if (char == "(") return ")"
        else if (char == "{") return "}"
        else if (char == "<") return ">"
        else if (char == "]") return "["
        else if (char == ")") return "("
        else if (char == "}") return "{"
        else if (char == ">") return "<"
        else return "?"
    }
}

open class Solution(fileName: String) {

    val file = File("aoc/2021/10", fileName)
    val lines = convertFile()
    private val scoreMap = hashMapOf(Pair(")", 3L), Pair("]", 57L), Pair("}", 1197L), Pair(">", 25137L), Pair("?", 0L))

    open fun solve(): Long {
        return lines.map { findFirstIncorrectChar(it) }.map { scoreMap[it]!! }.reduce { acc, score -> acc + score }
    }

    fun findFirstIncorrectChar(line: String): String {
        val charsList = line.toList().map { it.toString() }
        val list = LinkedList<String>()
        charsList.forEach {
            if (listOf("[", "(", "{", "<").contains(it)) list.add(it)
            else if (it == "]" && list.last == "[") list.removeLast()
            else if (it == ")" && list.last == "(") list.removeLast()
            else if (it == "}" && list.last == "{") list.removeLast()
            else if (it == ">" && list.last == "<") list.removeLast()
            else return it
        }
        return "?"
    }

    private fun convertFile(): List<String> {
        return file.readLines()
    }
}
