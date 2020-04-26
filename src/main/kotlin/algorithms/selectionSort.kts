package algorithms

fun selectionSort(arr: IntArray): IntArray {
    var arrSorted = arr
    val size = arr.size
    for (i in 0 until size) {
        var min = i
        for (j in i + 1 until size) {
            if (arrSorted[j] < arrSorted[min]) min = j
        }
        println(arrSorted.contentToString())
        arrSorted = swap(arrSorted, i, min)
    }
    return arrSorted
}

fun swap(arr: IntArray, i: Int, j: Int): IntArray {
    val el = arr[i]
    arr[i] = arr[j]
    arr[j] = el
    return arr
}

selectionSort(intArrayOf(4,8,1,6,3)).contentToString()