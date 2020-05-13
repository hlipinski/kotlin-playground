package algorithms

import kotlin.random.Random

fun insertionSort(arr: IntArray): IntArray {
    var arrSorted = arr
    val size = arr.size
    for (i in 1 until size) {
        var j = i
        while (j >= 1 && arrSorted[j] < arrSorted[j - 1]) {
            println(arrSorted.contentToString())
            arrSorted = swap(arrSorted, j - 1, j)
            j--
        }
    }
    return arrSorted
}

fun swap(arr: IntArray, i: Int, j: Int): IntArray {
    val el = arr[i]
    arr[i] = arr[j]
    arr[j] = el
    return arr
}

insertionSort(IntArray(10) { Random.nextInt(0, 100) }).contentToString()