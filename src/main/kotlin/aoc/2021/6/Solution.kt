package aoc.`2021`.`6`

import java.io.File
import java.math.BigInteger

fun main(args: Array<String>) {
    println("Part 1: ${Solution("input.txt").solve(80)}")
    println("Part 2: ${Solution("input.txt").solve(256)}")
}

class Solution(fileName: String) {

    val file = File("aoc/2021/6", fileName)

    fun solve(days: Int): BigInteger {
        return FishRegistry.dayPassed(days, convertFile()).values.reduce { acc, bigInteger -> acc.plus(bigInteger) }
    }

    fun convertFile(): HashMap<Int, BigInteger> {
        var map = hashMapOf<Int, BigInteger>()
        val list = file.readLines()[0].split(",").map { it.toInt() }
        list.forEach {
            map[it] = map.getOrDefault(it, BigInteger.ZERO).plus(BigInteger.ONE)
        }
        return map
    }
}

object FishRegistry {
    tailrec fun dayPassed(days: Int, initFishMap: Map<Int, BigInteger>): Map<Int, BigInteger> {
        if (days < 1) return initFishMap
        val newMap = HashMap<Int, BigInteger>()
        var newFish = BigInteger.ZERO
        var regeneratedFish = BigInteger.ZERO
        if (initFishMap.getOrDefault(0, BigInteger.ZERO) > BigInteger.ZERO) {
            newFish = initFishMap.getOrDefault(0, BigInteger.ZERO)
            regeneratedFish = newFish
            newMap[0] = BigInteger.ZERO
        }
        initFishMap.forEach { (key, value) ->
            if (key != 0) {
                newMap[key - 1] = value
            }
        }
        newMap[8] = newFish
        newMap[6] = newMap.getOrDefault(6, BigInteger.ZERO).plus(regeneratedFish)
        return dayPassed(days - 1, newMap)
    }
}
