package aoc.`2021`.`14`

import java.io.File
import java.math.BigInteger
import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    println("Part 1: ${Solution("input.txt").solve()}")
//    println("Part 2: ${Solution2("input.txt").solve()}")
}

class Solution2(fileName: String): Solution(fileName) {
    override fun solve(): BigInteger {
        var list = initPolymer
        IntRange(0, 39).forEach {
            list = step(list)
        }
        return calculateResult(list)
    }
}

open class Solution(fileName: String) {

    val file = File("aoc/2021/14", fileName)
    val initPolymer = convertFileToInitPolymer()
    val insertions = convertFileToInsertions()
    val lettersMap = hashMapOf<String, BigInteger>()

    open fun solve(): BigInteger {
        var list = initPolymer
        IntRange(0, 9).forEach {
            list = step(list)
        }
        return calculateResult(list)
    }

    fun calculateResult(list: List<String>): BigInteger {
        list.forEach { lettersMap[it] = lettersMap.getOrDefault(it, BigInteger.ZERO).add(BigInteger.ONE) }
        return lettersMap.values.max()!!.minus(lettersMap.values.min()!!)
    }

    fun step(list: List<String>): List<String> {
        val resultList = LinkedList<String>()
        val iterator = list.iterator()
        var first = iterator.next()
        resultList.add(first)
        while (iterator.hasNext()) {
            val second = iterator.next()
            val toInsert = insertions.getOrDefault("$first$second", "?")
            if (toInsert != "?") resultList.add(toInsert)
            resultList.add(second)
            first = second
        }

        return resultList
    }

    fun convertFileToInitPolymer(): List<String> {
        return file.readLines()[0].toList().map { it.toString() }
    }

    fun convertFileToInsertions(): HashMap<String, String> {
        val resultMap = hashMapOf<String, String>()
        file.readLines()
                .drop(2)
                .forEach {
                    val transformation = it.split(" -> ")
                    resultMap[transformation[0]] = transformation[1]
                }
        return resultMap
    }
}
