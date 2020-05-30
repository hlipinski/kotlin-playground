package exercises

/*
https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/528/week-1/3287/

Say you have an array prices for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
*/

fun main() {
    println(maxProfit(intArrayOf(7, 1, 5, 3, 6, 4))) // 7
    println(maxProfit(intArrayOf(1, 2, 3, 4, 5))) // 4
    println(maxProfit(intArrayOf(7, 6, 4, 3, 1))) // 0
}

fun maxProfit(prices: IntArray): Int {
    var lowestLocal = prices[0]
    var highestLocal = prices[0]
    var profit = 0
    for (i in 1 until prices.size) {
        if (highestLocal < prices[i]) highestLocal = prices[i]
        else if (highestLocal > prices[i]) {
            profit += highestLocal - lowestLocal
            lowestLocal = prices[i]
            highestLocal = prices[i]
        }
    }
    profit += highestLocal - lowestLocal
    return profit
}