package aoc.`2021`.`12`

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
        return findAllPaths().count()
    }

    override fun filterPaths(step: String, path: List<String>): Boolean {
        val singleSmallCaveVisitedTwice = path
                .filter { it != "start" }
                .filter { it != "end" }
                .filter { it.toCharArray()[0].isLowerCase() }
                .groupingBy { it }
                .eachCount()
                .all { it.value <= 1 }
        return step != "start" && (step.toCharArray()[0].isUpperCase() || (singleSmallCaveVisitedTwice && path.count { it == step } < 2) || !path.contains(step) || step == "end")
    }
}

open class Solution(fileName: String) {

    val file = File("aoc/2021/12", fileName)
    val caves = convertFile()
    val uniquePaths = hashSetOf<String>()

    open fun solve(): Int {
        return findAllPaths().count()
    }

    fun findAllPaths(): HashSet<String> {
        caves["start"]!!.forEach { goDeeper(it, listOf("start", it)) }
        return uniquePaths
    }

    private fun goDeeper(step: String, path: List<String>) {
        if (step == "end") {
            uniquePaths.add(path.joinToString(","))
            return
        }
        caves[step]!!
                .filter { filterPaths(it, path) }
                .forEach {
                    goDeeper(it, path + it)
                }
    }

    open fun filterPaths(step: String, path: List<String>): Boolean {
        return step.toCharArray()[0].isUpperCase() || !path.contains(step) || step == "end"
    }

    fun convertFile(): HashMap<String, Set<String>> {
        val resultMap = hashMapOf<String, Set<String>>()
        file.readLines().forEach {
            val caves = it.split("-")
            resultMap[caves[0]] = resultMap.getOrDefault(caves[0], hashSetOf()) + caves[1]
            resultMap[caves[1]] = resultMap.getOrDefault(caves[1], hashSetOf()) + caves[0]
        }
        return resultMap
    }
}
