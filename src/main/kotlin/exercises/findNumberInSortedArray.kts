package exercises

/*
Given a two dimensional matrix where each column and row are sorted in ascending order,
write a function checking if the matrix contains the requested number.

O(n+m)

11 16 33 45 55
12 19 35 55 56
18 20 60 61 80
20 71 74 78 81
66 72 90 94 99
 */

fun findNumberInSortedArray(arr: Array<IntArray>, element: Int): Boolean {
    var hi = arr[0].size - 1
    for (i in 0 until arr.size) {
        var lo = 0
        while (lo < hi) {
            val mid = (lo + hi) / 2
            println(arr[i][mid])
            if (element < arr[i][mid]) hi = mid - 1
            else if (element > arr[i][mid]) lo = mid + 1
            else return true
        }
    }
    return false
}

fun findNumberInSortedArray2(arr: Array<IntArray>, element: Int): Boolean {
    return checkNumber(arr, 0, 0, element)
}

fun checkNumber(arr: Array<IntArray>, i: Int, j: Int, el: Int): Boolean {
    println(arr[i][j])
    if (arr[i][j] > el) {
        return false
    } else if (arr[i][j] < el) {
        return checkNumber(arr, i + 1, j, el) || checkNumber(arr, i, j + 1, el) || checkNumber(arr, i + 1, j + 1, el)
    }
    return true
}

val row1 = intArrayOf(11, 16, 33, 45, 55)
val row2 = intArrayOf(12, 19, 35, 55, 56)
val row3 = intArrayOf(18, 20, 60, 61, 80)
val row4 = intArrayOf(20, 71, 74, 78, 81)
val row5 = intArrayOf(66, 72, 90, 94, 99)
val arr = arrayOf(row1, row2, row3, row4, row5)
findNumberInSortedArray(arr, 57)

/*
boolean isNumberInArray(int[][] array, int element) {
int end = array[0].lenght() - 1;
for (int i = 0; i < array.lenght; i++) {
	if (array[i][0] > element) return false;
	int start = 0;
	int middle = end / 2;
	while (end - start > 1) {
	if (array[i][middle] < element) {
		start = middle;
		middle = (end + start) / 2;
} else if (array[i][middle] > element) {
	end = middle;
	middle = (end + start) / 2;
} else {
	return true;
}
}
}
return false;
}

 */