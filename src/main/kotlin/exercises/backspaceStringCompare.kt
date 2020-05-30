package exercises

/*
https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/529/week-2/3291/

Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
Note that after backspacing an empty text, the text will continue empty.
*/

fun main() {
    println(backspaceCompare("ab##", "#cd#"))
}

fun backspaceCompare(S: String, T: String): Boolean {
    var sClean = StringBuilder()
    var tClean = StringBuilder()
    var sIdx = 0
    var tIdx = 0
    while (sIdx < S.length || tIdx < T.length) {
        if (sIdx < S.length) {
            if (S[sIdx] != '#') sClean.append(S[sIdx])
            else if (sClean.isNotEmpty()) sClean.deleteCharAt(sClean.length - 1)
            sIdx++
        }

        if (tIdx < T.length) {
            if (T[tIdx] != '#') tClean.append(T[tIdx])
            else if (tClean.isNotEmpty()) tClean.deleteCharAt(tClean.length - 1)
            tIdx++
        }
    }
    return sClean.toString() == tClean.toString()
}