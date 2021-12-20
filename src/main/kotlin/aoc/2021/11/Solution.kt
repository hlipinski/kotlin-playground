package aoc.`2021`.`11`

import java.io.File
import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {
    println("Part 1: ${Solution("input.txt").solve()}")
    println("Part 2: ${Solution2("input.txt").solve()}")
}

class Solution2(fileName: String): Solution(fileName) {

    override fun solve(): Int {
        var counter = 0
        while (flashed.count() < 100) {
            flashed.clear()
            step()
            tryToFlash()
            counter++
        }
        return counter
    }
}

open class Solution(fileName: String) {

    val file = File("aoc/2021/11", fileName)
    val octoMap = convertFile()
    val flashed = hashSetOf<String>()

    open fun solve(): Int {
        var counter = 0
        IntRange(0, 99).forEach {
            flashed.clear()
            step()
            tryToFlash()
            counter += flashed.count()
        }
        return counter
    }

    fun step() {
        octoMap.keys.forEach {
            octoMap[it] = octoMap[it]!! + 1
        }
    }

    fun tryToFlash() {
        octoMap.keys.forEach { flash(it) }
    }

    fun flash(key: String) {
        if (octoMap[key]!! <= 9 || flashed.contains(key)) return

        flashed.add(key)
        octoMap[key] = 0

        getNeighbours(key)
                .filter { !flashed.contains(it) }
                .forEach {
                    increaseByFlash(it)
                    flash(it)
                }
    }

    fun increaseByFlash(key: String) {
        octoMap[key] = if (octoMap[key]!! == 0) 0 else octoMap[key]!! + 1
    }

    private fun convertFile(): HashMap<String, Int> {
        var row = 0
        val resultMap = hashMapOf<String, Int>()
        val lines = file.readLines()
        lines.forEach {
            it.forEachIndexed { index, c -> resultMap.put("$row|$index", c.toString().toInt()) }
            row++
        }
        return resultMap
    }

    private fun printArr() {
        println("${octoMap["0|0"]}${octoMap["0|1"]}${octoMap["0|2"]}${octoMap["0|3"]}${octoMap["0|4"]}${octoMap["0|5"]}${octoMap["0|6"]}${octoMap["0|7"]}${octoMap["0|8"]}${octoMap["0|9"]}")
        println("${octoMap["1|0"]}${octoMap["1|1"]}${octoMap["1|2"]}${octoMap["1|3"]}${octoMap["1|4"]}${octoMap["1|5"]}${octoMap["1|6"]}${octoMap["1|7"]}${octoMap["1|8"]}${octoMap["1|9"]}")
        println("${octoMap["2|0"]}${octoMap["2|1"]}${octoMap["2|2"]}${octoMap["2|3"]}${octoMap["2|4"]}${octoMap["2|5"]}${octoMap["2|6"]}${octoMap["2|7"]}${octoMap["2|8"]}${octoMap["2|9"]}")
        println("${octoMap["3|0"]}${octoMap["3|1"]}${octoMap["3|2"]}${octoMap["3|3"]}${octoMap["3|4"]}${octoMap["3|5"]}${octoMap["3|6"]}${octoMap["3|7"]}${octoMap["3|8"]}${octoMap["3|9"]}")
        println("${octoMap["4|0"]}${octoMap["4|1"]}${octoMap["4|2"]}${octoMap["4|3"]}${octoMap["4|4"]}${octoMap["4|5"]}${octoMap["4|6"]}${octoMap["4|7"]}${octoMap["4|8"]}${octoMap["4|9"]}")
        println("${octoMap["5|0"]}${octoMap["5|1"]}${octoMap["5|2"]}${octoMap["5|3"]}${octoMap["5|4"]}${octoMap["5|5"]}${octoMap["5|6"]}${octoMap["5|7"]}${octoMap["5|8"]}${octoMap["5|9"]}")
        println("${octoMap["6|0"]}${octoMap["6|1"]}${octoMap["6|2"]}${octoMap["6|3"]}${octoMap["6|4"]}${octoMap["6|5"]}${octoMap["6|6"]}${octoMap["6|7"]}${octoMap["6|8"]}${octoMap["6|9"]}")
        println("${octoMap["7|0"]}${octoMap["7|1"]}${octoMap["7|2"]}${octoMap["7|3"]}${octoMap["7|4"]}${octoMap["7|5"]}${octoMap["7|6"]}${octoMap["7|7"]}${octoMap["7|8"]}${octoMap["7|9"]}")
        println("${octoMap["8|0"]}${octoMap["8|1"]}${octoMap["8|2"]}${octoMap["8|3"]}${octoMap["8|4"]}${octoMap["8|5"]}${octoMap["8|6"]}${octoMap["8|7"]}${octoMap["8|8"]}${octoMap["8|9"]}")
        println("${octoMap["9|0"]}${octoMap["9|1"]}${octoMap["9|2"]}${octoMap["9|3"]}${octoMap["9|4"]}${octoMap["9|5"]}${octoMap["9|6"]}${octoMap["9|7"]}${octoMap["9|8"]}${octoMap["9|9"]}")
    }

    fun getNeighbours(key: String): Set<String> {
        val maxRowIdx = 9
        val maxColIdx = 9
        return if (key == "0|0") hashSetOf("0|1", "1|0", "1|1")
        else if (key == "0|${maxColIdx}") hashSetOf("0|${maxColIdx - 1}", "1|${maxColIdx}", "1|${maxColIdx - 1}")
        else if (key == "${maxRowIdx}|0") hashSetOf("${maxRowIdx - 1}|0", "${maxRowIdx}|1", "${maxRowIdx - 1}|1")
        else if (key == "${maxRowIdx}|${maxColIdx}") hashSetOf("${maxRowIdx}|${maxColIdx - 1}", "${maxRowIdx - 1}|${maxColIdx}", "${maxRowIdx - 1}|${maxColIdx - 1}")
        else {
            val idx = key.split("|").map { it.toInt() }
            if (idx[0] == 0) hashSetOf("0|${idx[1] - 1}", "1|${idx[1]}", "0|${idx[1] + 1}", "1|${idx[1] - 1}", "1|${idx[1] + 1}")
            else if (idx[0] == maxRowIdx) hashSetOf("$maxRowIdx|${idx[1] - 1}", "${maxRowIdx - 1}|${idx[1]}", "$maxRowIdx|${idx[1] + 1}", "${maxRowIdx - 1}|${idx[1] - 1}", "${maxRowIdx - 1}|${idx[1] + 1}")
            else if (idx[1] == 0) hashSetOf("${idx[0] - 1}|0", "${idx[0]}|1", "${idx[0] + 1}|0", "${idx[0] - 1}|1", "${idx[0] + 1}|1")
            else if (idx[1] == maxColIdx) hashSetOf("${idx[0] - 1}|$maxColIdx", "${idx[0]}|${maxColIdx - 1}", "${idx[0] + 1}|$maxColIdx", "${idx[0] - 1}|${maxColIdx - 1}", "${idx[0] + 1}|${maxColIdx - 1}")
            else {
                hashSetOf("${idx[0] - 1}|${idx[1] - 1}",
                        "${idx[0] - 1}|${idx[1]}",
                        "${idx[0] - 1}|${idx[1] + 1}",
                        "${idx[0] + 1}|${idx[1]}",
                        "${idx[0] + 1}|${idx[1] - 1}",
                        "${idx[0]}|${idx[1] - 1}",
                        "${idx[0]}|${idx[1] + 1}",
                        "${idx[0] + 1}|${idx[1] + 1}")
            }
        }
    }
}
