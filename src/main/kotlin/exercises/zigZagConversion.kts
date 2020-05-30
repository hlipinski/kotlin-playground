package exercises

//https://leetcode.com/problems/zigzag-conversion/

fun convert(s: String, numRows: Int): Array<String?> {
    var result = ""
    var list = arrayOfNulls<String>(numRows)
    for (i in s.indices) {
        var idx = i % numRows
        list[idx] = (list.getOrNull(idx) ?: "") + s.substring(i, i + 1)
    }
    return list
}

convert("PAYPALISHIRING", 3).contentToString() // result: "PAHNAPLSIIGYIR"

// 1 row -> 4
// 2 row -> 2
// 3 row -> 3

// 1 row -> 6
// 2 row -> 3
// 3 row -> 3