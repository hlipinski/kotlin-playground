import java.io.File
import kotlin.math.pow

var common = mutableListOf<Int>(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
val file = File("input.txt")
val signals = file.readLines()
println(signals.size)

signals.forEach {
    it.forEachIndexed { index, c ->
        if (c == '1') common[index]++
        else common[index]--
    }
}
println("common $common")

val gamma = common.reduceIndexed { index, acc, i -> if (i > 0) acc + ("2".toDouble().pow((11 - index).toDouble())).toInt() else acc + 0 }
val gammaBin = common.map { if (it > 0) "1" else "0" }
val gammaBinReduced = gammaBin.reduce { acc, i -> acc + i }

val delta = common.reduceIndexed { index, acc, i -> if (i > 0) acc + 0 else acc + ("2".toDouble().pow((11 - index).toDouble())).toInt() }
val deltaBin = common.map { if (it > 0) "0" else "1" }
val deltaBinReduced = deltaBin.reduce { acc, i -> acc + i }

println("gamma $gamma")
println("gammaBin $gammaBin")
println("gammaBinReduced $gammaBinReduced")
println("delta $delta")
println("deltaBin $deltaBin")
println("deltaBinReduced $deltaBinReduced")

println(gammaBinReduced.toInt(2) * deltaBinReduced.toInt(2))

println("------------ PART 2 ---------------")

var mostCommonList = signals
var idx = 0
while (true) {
    if (mostCommonList.size <= 1) break
    val mostCommonBit = if (findMostCommon(mostCommonList, idx) >= 0) '1' else '0'
    mostCommonList = mostCommonList.filter { it[idx] == mostCommonBit }
    idx++
}

println("mostCommonList $mostCommonList")

var leastCommonList = signals
idx = 0
while (true) {
    if (leastCommonList.size <= 1) break
    val leastCommonBit = if (findMostCommon(leastCommonList, idx) >= 0) '0' else '1'
    leastCommonList = leastCommonList.filter { it[idx] == leastCommonBit }
    idx++
}

println("leastCommonList $leastCommonList")

println(mostCommonList[0].toInt(2) * leastCommonList[0].toInt(2))

fun findMostCommon(list: List<String>, idx: Int): Int {
    var counter = 0;
    list.forEach {
        if (it[idx] == '1') counter++
        else counter--
    }

    return counter
}
