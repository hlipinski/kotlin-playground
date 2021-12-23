package aoc.`2021`.`15`

import java.io.File

fun main(args: Array<String>) {
    println("Part 1: ${Solution("input.txt").solve()}")
//    println("Part 2: ${Solution2("input.txt").solve()}") 446
}

typealias Point = Pair<Int, Int>

open class Solution(fileName: String) {

    val file = File("aoc/2021/15", fileName)
    private val maxBounds = convertFileToMaxBounds()
    val caves = convertFileToCaves()
    private var fastestPath = generateExamplePath()
    val neighbours = generateNeighbours()

    open fun solve(): Int {
        findPath()
        return fastestPath
    }

    fun findPath() {
        println("fastestPath: $fastestPath")
        goDeeper(Point(0, 0), hashSetOf(Point(0, 0)), 0)
    }

    private fun goDeeper(step: Point, path: Set<Point>, pathValue: Int) {
        if (pathValue + distanceFromEnd(step) >= fastestPath) return
        if (step == Point(maxBounds.first, maxBounds.second)) {
            fastestPath = pathValue
            println("fastestPath: $fastestPath, steps: ${path.size}")
            return
        }

        neighbours[step]!!
                .filter { filterPaths(it, path) }
                .sortedBy { caves[it] }
                .forEach {
                    goDeeper(it, path + it, pathValue + caves[it]!!)
                }
    }

    open fun filterPaths(step: Point, path: Set<Point>): Boolean {
        return step !in path || step == Point(maxBounds.first, maxBounds.second)
    }

    fun generateNeighbours(): HashMap<Point, List<Point>> {
        val result = hashMapOf<Point, List<Point>>()
        IntRange(0, maxBounds.first).forEach { row ->
            IntRange(0, maxBounds.first).forEach {  col ->
                val key = Point(row, col)
                result[key] = getNeighbours(key)
            } }
        return result
    }

    private fun getNeighbours(key: Point): List<Point> = listOf(
//            Point(key.first - 1, key.second),
//            Point(key.first, key.second - 1),
            Point(key.first + 1, key.second),
            Point(key.first, key.second + 1)
    ).filter { it.first >= 0 && it.second >= 0 && it.first <= maxBounds.first && it.second <= maxBounds.second }

    fun distanceFromEnd(key: Point): Int = maxBounds.first - key.first + maxBounds.second - key.second

    private fun generateExamplePath(): Int {
        var path = 0
        IntRange(0, maxBounds.first).forEach { path += caves[Point(it, 0)]!! }
        IntRange(0, maxBounds.second).forEach { path += caves[Point(0, it)]!! }
        return path
    }

    fun convertFileToCaves(): HashMap<Point, Int> {
        val resultMap = hashMapOf<Point, Int>()
        file.readLines()
                .forEachIndexed { colIdx, col ->
                    col.toList()
                            .map { it.toString().toInt() }
                            .forEachIndexed { rowIdx, row ->
                                resultMap[Point(rowIdx, colIdx)] = row
                            }
                }
        return resultMap
    }

    fun convertFileToMaxBounds(): Point {
        val lines = file.readLines()
        return Point(lines.size - 1, lines[0].length - 1)
    }
}
