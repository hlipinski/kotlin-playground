package algorithms

import kotlin.random.Random

fun quickSort(arr: IntArray): IntArray {
    if (arr.size <= 2) return arr

    var resultArr = arr
    var lo = resultArr[0]
    var i = 1
    var j = resultArr.size - 1

    while (i <= j) {
        when {
            resultArr[i] <= lo -> i++
            resultArr[j] >= lo -> j--
            resultArr[i] > lo && resultArr[j] < lo -> {
                resultArr = swap(resultArr, i , j)
            }
        }
    }

    resultArr = swap(resultArr, 0, j)

    if (j == 0) return intArrayOf(lo) + quickSort(arr.copyOfRange(1, resultArr.size))
    if (i == resultArr.size - 1) return quickSort(arr.copyOfRange(1, resultArr.size)) + intArrayOf(lo)

    val leftArr = quickSort(arr.copyOfRange(0, j))
    val rightArr = quickSort(arr.copyOfRange(j, resultArr.size))
    return leftArr + rightArr
}

fun swap(arr: IntArray, i: Int, j: Int): IntArray {
    val el = arr[i]
    arr[i] = arr[j]
    arr[j] = el
    return arr
}

quickSort(IntArray(10) { Random.nextInt(0, 100) }).contentToString()