package exercises

/*
https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/528/week-1/3285/

Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
*/

fun main() {
    println(maxSubArray(intArrayOf(-2, -3, 4, -1, 2, 1, -5, 4))) // [4,-1,2,1] => 6
}

fun maxSubArray(nums: IntArray): Int {
    var currSum = Int.MIN_VALUE
    var maxSum = Int.MIN_VALUE
    for (i in 0 until nums.size) {
        currSum = if (currSum < 0) 0 else currSum
        currSum += nums[i]
        maxSum = if (currSum > maxSum) currSum else maxSum
    }
    return maxSum
}