package exercises

/*
https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/528/week-1/3286/

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
*/

fun main() {
    moveZeroes(intArrayOf(0, 1, 0, 3, 12)) // [1,3,12,0,0]
}

fun moveZeroes(nums: IntArray): Unit {
    var i = 0
    while (i < nums.size - 1) {
        if (nums[i] == 0) swap(nums, i)
        i++
    }
}

fun swap(nums: IntArray, i: Int): Unit {
    if (i >= nums.size - 1) return
    if (nums[i + 1] == 0) swap(nums, i + 1)
    val tmp = nums[i]
    nums[i] = nums[i + 1]
    nums[i + 1] = tmp
}