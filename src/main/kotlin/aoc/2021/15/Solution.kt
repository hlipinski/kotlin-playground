package aoc.`2021`.`15`

import java.io.File
import java.util.*

fun main(args: Array<String>) {
//    println("Part 1: ${Solution("input.txt").solve()}")
    println("Part 2: ${Solution2("input.txt").solve()}")
}

typealias Point = Pair<Int, Int>

class Solution2(fileName: String): Solution(fileName) {
    private val oldMaxBounds = Point(maxBounds.first + 1, maxBounds.second + 1)

    override fun solve(): Int {
        val start = System.currentTimeMillis()
        enlargeCaves()
        val solution = super.solve()
        println("Execution time: ${System.currentTimeMillis() - start}")
        return solution
    }

    private fun enlargeCaves() {
        maxBounds = Point(oldMaxBounds.first * 5 - 1, oldMaxBounds.second * 5 - 1)
    }

    override fun getRiskLevel(point: Point): Int {
        val rowFactor = if (point.first < oldMaxBounds.first) 0
        else point.first / oldMaxBounds.first

        val colFactor = if (point.second < oldMaxBounds.second) 0
        else point.second / oldMaxBounds.second

        return increaseRiskLevel(caves[Point(
                point.first - oldMaxBounds.first * rowFactor,
                point.second - oldMaxBounds.second * colFactor
        )]!!, rowFactor + colFactor)
    }

    private fun increaseRiskLevel(oldRiskLvl: Int, factor: Int): Int {
        val newRiskLvl = oldRiskLvl + factor
        return if (newRiskLvl > 9) newRiskLvl - 9
        else newRiskLvl
    }
}

open class Solution(fileName: String) {

    val file = File("aoc/2021/15", fileName)
    var maxBounds = convertFileToMaxBounds()
    val caves = convertFileToCaves()
    private val visited = hashSetOf<Point>()
    private val queue = PriorityQueue<Pair<Point, Int>> { o1, o2 -> o1.second - o2.second }

    open fun solve(): Int = findPath()

    fun findPath() = dijkstra(Point(0, 0), hashMapOf(Pair(Point(0, 0), 0)))

    private tailrec fun dijkstra(step: Point, graph: HashMap<Point, Int>): Int {
        if (step == Point(maxBounds.first, maxBounds.second)) return graph[step]!!
        visited.add(step)

        getNeighbours(step)
                .filter { it !in visited }
                .filter { it !in graph.keys }
                .forEach {
                    val riskLevel = getRiskLevel(it)
                    graph[it] = graph[step]!! + riskLevel
                    queue.add(Pair(it, graph[step]!! + riskLevel))
                }

        return dijkstra(queue.poll().first, graph)
    }

    open fun getRiskLevel(point: Point) = caves[point]!!

    private fun getNeighbours(key: Point): List<Point> = listOf(
            Point(key.first - 1, key.second),
            Point(key.first, key.second - 1),
            Point(key.first + 1, key.second),
            Point(key.first, key.second + 1)
    ).filter { it.first >= 0 && it.second >= 0 && it.first <= maxBounds.first && it.second <= maxBounds.second }

    private fun convertFileToCaves(): HashMap<Point, Int> {
        val resultMap = hashMapOf<Point, Int>()
        file.readLines()
                .forEachIndexed { rowIdx, row ->
                    row.toList()
                            .map { it.toString().toInt() }
                            .forEachIndexed { colIdx, col ->
                                resultMap[Point(rowIdx, colIdx)] = col
                            }
                }
        return resultMap
    }

    private fun convertFileToMaxBounds(): Point {
        val lines = file.readLines()
        return Point(lines.size - 1, lines[0].length - 1)
    }
}
