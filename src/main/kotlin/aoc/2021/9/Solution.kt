package aoc.`2021`.`9`

import java.io.File

fun main(args: Array<String>) {
    println("Part 1: ${Solution("input.txt").solve()}")
    println("Part 2: ${Solution2("input.txt").solve()}")
}

class Solution2(fileName: String): Solution(fileName) {
    val visited = hashSetOf<String>()
    val basinPoints = hashSetOf<String>()

    override fun solve(): Int {
        val lowPoints = locations.map.filter { (k, v) -> getNeighbours(k).all { v < locations.map[it]!! } }
        val basinSizes = lowPoints.keys.map { countBasinSize(it) }
        println(basinSizes)
        return basinSizes.sorted().takeLast(3).reduce { acc, num -> acc * num }
    }

    fun countBasinSize(key: String): Int {
        basinPoints.clear()
        goDeeper(key)
        return basinPoints.size
    }

    fun goDeeper(key: String) {
        visited.add(key)
        basinPoints.add(key)
        val value = locations.map[key]!!

        getNeighbours(key)
                .filter { locations.map[it]!! < 9 }
                .filter { value < locations.map[it]!! }
                .filter { !visited.contains(it) }
                .forEach{ goDeeper(it) }
    }
}

open class Solution(fileName: String) {

    val file = File("aoc/2021/9", fileName)
    val locations = convertFile()

    open fun solve(): Int {
        val values = locations.map.filter { (k, v) -> getNeighbours(k).all { v < locations.map[it]!! } }.values.map { it + 1 }
        return values.reduce { acc, num -> acc + num }
    }

    fun getNeighbours(key: String): Set<String> {
        val maxRowIdx = locations.rows - 1
        val maxColIdx = locations.cols - 1
        return if (key == "0|0") hashSetOf("0|1", "1|0")
        else if (key == "0|${maxColIdx}") hashSetOf("0|${maxColIdx - 1}", "1|${maxColIdx}")
        else if (key == "${maxRowIdx}|0") hashSetOf("${maxRowIdx - 1}|0", "${maxRowIdx}|1")
        else if (key == "${maxRowIdx}|${maxColIdx}") hashSetOf("${maxRowIdx}|${maxColIdx - 1}", "${maxRowIdx - 1}|${maxColIdx}")
        else {
            val idx = key.split("|").map { it.toInt() }
            if (idx[0] == 0) hashSetOf("0|${idx[1] - 1}", "1|${idx[1]}", "0|${idx[1] + 1}")
            else if (idx[0] == maxRowIdx) hashSetOf("$maxRowIdx|${idx[1] - 1}", "${maxRowIdx - 1}|${idx[1]}", "$maxRowIdx|${idx[1] + 1}")
            else if (idx[1] == 0) hashSetOf("${idx[0] - 1}|0", "${idx[0]}|1", "${idx[0] + 1}|0")
            else if (idx[1] == maxColIdx) hashSetOf("${idx[0] - 1}|$maxColIdx", "${idx[0]}|${maxColIdx - 1}", "${idx[0] + 1}|$maxColIdx")
            else {
                hashSetOf("${idx[0] - 1}|${idx[1]}", "${idx[0] + 1}|${idx[1]}", "${idx[0]}|${idx[1] - 1}", "${idx[0]}|${idx[1] + 1}")
            }
        }
    }

    private fun convertFile(): Locations {
        var row = 0
        val resultMap = hashMapOf<String, Int>()
        val lines = file.readLines()
        lines.forEach {
            it.forEachIndexed { index, c -> resultMap.put("$row|$index", c.toString().toInt()) }
            row++
        }
        return Locations(lines.size, lines[0].length, resultMap)
    }
}

class Locations(val rows: Int, val cols: Int, val map: Map<String, Int>)
