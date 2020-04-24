package algorithms

fun binarySearch(array: Array<Int>, key: Int): Int {
    var lo = 0
    var hi = array.size - 1
    while (lo < hi) {
        val mid = lo + (hi - lo) / 2
        when {
            key < mid -> hi = mid - 1
            key > mid -> lo = mid + 1
            else -> return mid
        }
    }
    return -1
}