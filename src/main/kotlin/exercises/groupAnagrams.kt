package exercises

/*
https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/528/week-1/3288/

Given an array of strings, group anagrams together.
*/

fun main() {
    println(groupAnagrams(arrayOf("eat","tea","tan","ate","nat","bat"))) // [[eat, tea, ate], [tan, nat], [bat]]
}

fun groupAnagrams(strs: Array<String>): List<List<String>> {
    var result = mutableMapOf<String, MutableList<String>>()
    for (i in strs.indices) {
        val s = strs[i]
        var charsSum = strSorter(s)
        var list = result.get(charsSum) ?: mutableListOf()
        list.add(s)
        result.put(charsSum, list)
    }
    return result.map { entry -> entry.value }
}

fun strSorter(s: String) = s.toCharArray().sorted().toString()