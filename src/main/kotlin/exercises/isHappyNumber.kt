package exercises

/*
https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/528/week-1/3284/

Write an algorithm to determine if a number n is "happy".
A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits,
and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy numbers.
Return True if n is a happy number, and False if not.
*/

fun isHappy(n: Int): Boolean {
    var num = n
    val accSet = HashSet<Int>()
    while (true) {
        val nStr = "" + num
        var accumulator = 0
        for (i in nStr.toCharArray()) {
            val integ = Integer.parseInt(i.toString())
            accumulator += integ * integ
        }
        if (accumulator == 1) return true
        if (accSet.contains(accumulator)) return false
        println(accumulator)
        accSet.add(accumulator)
        num = accumulator
    }
}

fun main() {
    isHappy(19)
}