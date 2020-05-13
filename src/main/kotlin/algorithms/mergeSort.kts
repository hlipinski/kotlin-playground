package algorithms

import kotlin.random.Random

fun mergeSort(arr: IntArray): IntArray {
    if (arr.size <= 1) return arr

    val leftArr = mergeSort(arr.copyOfRange(0, arr.size / 2))
    val rightArr = mergeSort(arr.copyOfRange(arr.size / 2, arr.size))

    var i = 0
    var j = 0
    for (k in arr.indices) {
        arr[k] = when {
            j >= rightArr.size -> leftArr[i++]
            i >= leftArr.size -> rightArr[j++]
            leftArr[i] > rightArr[j] -> rightArr[j++]
            else -> leftArr[i++]
        }
    }
    return arr
}

mergeSort(IntArray(10) { Random.nextInt(0, 100) }).contentToString()