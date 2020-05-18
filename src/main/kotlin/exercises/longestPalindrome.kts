package exercises

//https://leetcode.com/problems/longest-palindromic-substring/

fun longestPalindrome(s: String): String {
    if (s == "") return s

    val arrS = s.toCharArray()
    var element = arrS[0]
    var longestPalindrome = element.toString()

    for (i in 0..arrS.size) {
        var palindrome1 = expandAroundCenter(arrS, i, i)
        var palindrome2 = expandAroundCenter(arrS, i, i + 1)

        if (longestPalindrome.length < palindrome1.length) {
            longestPalindrome = palindrome1
        }
        if (longestPalindrome.length < palindrome2.length) {
            longestPalindrome = palindrome2
        }
    }
    return longestPalindrome
}

fun expandAroundCenter(arrS: CharArray, l: Int, r: Int): String {
    var left = l
    var right = r
    var palindrome = ""
    while (left >= 0 && right < arrS.size && arrS[left] == arrS[right]) {
        palindrome = if (left == right) arrS[left].toString() else "${arrS[left]}$palindrome${arrS[right]}"
        left--
        right++
    }
    return palindrome
}

longestPalindrome("aaabaaaa")
longestPalindrome("ababd")
longestPalindrome("bb")