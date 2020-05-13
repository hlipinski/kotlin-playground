package algorithms

fun mergeSort(arr: IntArray): IntArray {
    if (arr.size <= 1) return arr

    val leftArr = mergeSort(arr.copyOfRange(0, arr.size / 2))
    val rightArr = mergeSort(arr.copyOfRange(arr.size / 2, arr.size))

    var i = 0
    var j = 0
    for (k in arr.indices) {
        when {
            j >= rightArr.size -> arr[k] = leftArr[i++]
            i >= leftArr.size -> arr[k] = rightArr[j++]
            leftArr[i] > rightArr[j] -> arr[k] = rightArr[j++]
            else -> arr[k] = leftArr[i++]
        }
    }
    return arr
}

mergeSort(intArrayOf(41,5,19,123,20,221,9,13,16,31)).contentToString()