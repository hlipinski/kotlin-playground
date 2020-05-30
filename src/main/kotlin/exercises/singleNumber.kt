package exercises

import java.util.HashSet


/*
https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/528/week-1/3283/

Given a non-empty array of integers, every element appears twice except for one. Find that single one.
*/

fun main() {
    println(singleNumber(intArrayOf(2, 2, 1))) // 1
    println(singleNumber(intArrayOf(4, 1, 2, 1, 2))) // 4
}

fun singleNumber(nums: IntArray): Int {
    val set = HashSet<Int>()
    for (i in nums.indices) {
        val num = Integer.valueOf(nums[i])
        if (set.contains(num)) {
            set.remove(num)
        } else {
            set.add(num)
        }
    }
    return set.toTypedArray()[0] as Int
}