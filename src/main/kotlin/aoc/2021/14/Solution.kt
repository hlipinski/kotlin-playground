package aoc.`2021`.`14`

import java.io.File
import java.math.BigInteger
import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    println("Part 1: ${Solution("input.txt").solve()}")
    println("Part 2: ${Solution2("input.txt").solve()}")
}

class Solution2(fileName: String): Solution(fileName) {
    override fun solve(): BigInteger {
        var map = polymer
        IntRange(0, 39).forEach {
            map = step(map)
        }
        return lettersMap.values.max()!!.minus(lettersMap.values.min()!!)
    }
}

open class Solution(fileName: String) {

    val file = File("aoc/2021/14", fileName)
    val lettersMap = hashMapOf<String, BigInteger>()
    val polymer = convertFileToInitPolymer()
    val insertions = convertFileToInsertions()

    open fun solve(): BigInteger {
        var map = polymer
        IntRange(0, 9).forEach {
            map = step(map)
        }
        return lettersMap.values.max()!!.minus(lettersMap.values.min()!!)
    }

    fun step(polymer: Map<String, BigInteger>): Map<String, BigInteger> {
        val resultMap = hashMapOf<String, BigInteger>()
        polymer.keys.forEach {
            val toInsert = insertions.getOrDefault(it, "?")
            if (toInsert != "?") {
                resultMap["${it[0]}$toInsert"] = resultMap.getOrDefault("${it[0]}$toInsert", BigInteger.ZERO).plus(polymer.getOrDefault(it, BigInteger.ZERO))
                resultMap["$toInsert${it[1]}"] = resultMap.getOrDefault("$toInsert${it[1]}", BigInteger.ZERO).plus(polymer.getOrDefault(it, BigInteger.ZERO))
                lettersMap[toInsert] = lettersMap.getOrDefault(toInsert, BigInteger.ZERO).add(polymer.getOrDefault(it, BigInteger.ZERO))
            } else {
                resultMap[it] = resultMap.getOrDefault(it, BigInteger.ZERO).plus(BigInteger.ONE)
            }
        }
        return resultMap
    }

    fun convertFileToInitPolymer(): Map<String, BigInteger> {
        val resultMap = hashMapOf<String, BigInteger>()
        val iterator = file.readLines()[0].toList().map { it.toString() }.iterator()
        var first = iterator.next()
        lettersMap[first] = BigInteger.ONE
        while (iterator.hasNext()) {
            val second = iterator.next()
            resultMap["$first$second"] = resultMap.getOrDefault("$first$second", BigInteger.ZERO).plus(BigInteger.ONE)
            lettersMap[second] = lettersMap.getOrDefault(second, BigInteger.ZERO).add(BigInteger.ONE)
            first = second
        }
        return resultMap
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
