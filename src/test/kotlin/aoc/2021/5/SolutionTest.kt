package aoc.`2021`.`5`

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Solution2Test {

    private val solution2 = Solution2("test_input.txt")

    @Test
    fun shouldSolve() {
        assertThat(solution2.solve()).isEqualTo(12);
    }

    @Test
    fun shouldMapToHashMap() {
        val solution = solution2.convertFile(listOf(
                "0,9 -> 5,9",
                "8,0 -> 0,8",
                "9,4 -> 3,4",
                "2,2 -> 2,1",
                "7,0 -> 7,4",
                "6,4 -> 2,0",
                "0,9 -> 2,9",
                "3,4 -> 1,4",
                "0,0 -> 8,8",
                "5,5 -> 8,2"
        ))
        assertThat(solution).isEqualTo(
                hashMapOf(
                        Pair("0|0", 1), Pair("2|0", 1), Pair("7|0", 1), Pair("8|0", 1),
                        Pair("1|1", 1), Pair("2|1", 1), Pair("3|1", 1), Pair("7|1", 2),
                        Pair("2|2", 2), Pair("4|2", 1), Pair("6|2", 1), Pair("7|2", 1), Pair("8|2", 1),
                        Pair("3|3", 1), Pair("5|3", 2), Pair("7|3", 2),
                        Pair("1|4", 1), Pair("2|4", 1), Pair("3|4", 2), Pair("4|4", 3), Pair("5|4", 1), Pair("6|4", 3), Pair("7|4", 2), Pair("8|4", 1), Pair("9|4", 1),
                        Pair("3|5", 1), Pair("5|5", 2),
                        Pair("2|6", 1), Pair("6|6", 1),
                        Pair("1|7", 1), Pair("7|7", 1),
                        Pair("0|8", 1), Pair("8|8", 1),
                        Pair("0|9", 2), Pair("1|9", 2), Pair("2|9", 2), Pair("3|9", 1), Pair("4|9", 1), Pair("5|9", 1)
                )
        )
    }

    @Test
    fun shouldMapToOneLine() {
        val solution = solution2.convertFile(listOf("0,9 -> 5,9"))
        assertThat(solution).isEqualTo(
                hashMapOf(Pair("0|9", 1), Pair("1|9", 1), Pair("2|9", 1), Pair("3|9", 1), Pair("4|9", 1), Pair("5|9", 1))
        )
    }

    @Test
    fun shouldMapToOneLineDiagonal() {
        val solution = solution2.convertFile(listOf("6,4 -> 2,0"))
        assertThat(solution).isEqualTo(
                hashMapOf(Pair("2|0", 1), Pair("3|1", 1), Pair("4|2", 1), Pair("5|3", 1), Pair("6|4", 1))
        )
    }

    @Test
    fun shouldMapToReversedDiagonal() {
        val solution = solution2.convertFile(listOf("5,5 -> 8,2"))
        assertThat(solution).isEqualTo(
                hashMapOf(Pair("8|2", 1), Pair("7|3", 1), Pair("6|4", 1), Pair("5|5", 1))
        )
    }

    @Test
    fun shouldMapToReversedDiagonal_2() {
        val solution = solution2.convertFile(listOf("8,2 -> 6,4"))
        assertThat(solution).isEqualTo(
                hashMapOf(Pair("8|2", 1), Pair("7|3", 1), Pair("6|4", 1))
        )
    }

    @Test
    fun shouldMapToReversedDiagonal_3() {
        val solution = solution2.convertFile(listOf("8,0 -> 0,8"))
        assertThat(solution).isEqualTo(
                hashMapOf(
                        Pair("8|0", 1),
                        Pair("7|1", 1),
                        Pair("6|2", 1),
                        Pair("5|3", 1),
                        Pair("4|4", 1),
                        Pair("3|5", 1),
                        Pair("2|6", 1),
                        Pair("1|7", 1),
                        Pair("0|8", 1)
                )
        )
    }

    @Test
    fun shouldMapToDiagonal() {
        val solution = solution2.convertFile(listOf("0,0 -> 8,8"))
        assertThat(solution).isEqualTo(
                hashMapOf(
                        Pair("0|0", 1),
                        Pair("1|1", 1),
                        Pair("2|2", 1),
                        Pair("3|3", 1),
                        Pair("4|4", 1),
                        Pair("5|5", 1),
                        Pair("6|6", 1),
                        Pair("7|7", 1),
                        Pair("8|8", 1)
                )
        )
    }
}

class Solution1Test {

    private val solution1 = Solution1("test_input.txt")

    @Test
    fun shouldSolve() {
        assertThat(solution1.solve()).isEqualTo(5);
    }

    @Test
    fun shouldMapToHashMap() {
        assertThat(solution1.convertFile()).isEqualTo(
                hashMapOf(
                        Pair("7|0", 1),
                        Pair("2|1", 1), Pair("7|1", 1),
                        Pair("2|2", 1), Pair("7|2", 1),
                        Pair("7|3", 1),
                        Pair("1|4", 1), Pair("2|4", 1), Pair("3|4", 2), Pair("4|4", 1), Pair("5|4", 1), Pair("6|4", 1), Pair("7|4", 2), Pair("8|4", 1), Pair("9|4", 1),
                        Pair("0|9", 2), Pair("1|9", 2), Pair("2|9", 2), Pair("3|9", 1), Pair("4|9", 1), Pair("5|9", 1)
                )
        )
    }

    @Test
    fun shouldMapToOneLine() {
        val solution = Solution1("test_input_2.txt")
        assertThat(solution.convertFile()).isEqualTo(
                hashMapOf(Pair("0|9", 1), Pair("1|9", 1), Pair("2|9", 1), Pair("3|9", 1), Pair("4|9", 1), Pair("5|9", 1))
        )
    }

    @Test
    fun shouldMapToEmpty() {
        val solution = Solution1("test_input_empty.txt")
        assertThat(solution.convertFile()).isEqualTo(hashMapOf<String, Int>())
    }
}

class SolutionTest {

    private val solution1 = Solution1("test_input.txt")

    @Test
    fun shouldParseLineCorrectly() {
        assertThat(solution1.parseLine("0,9 -> 5,9")).isEqualTo(
                Pair(listOf(0, 9), listOf(5, 9))
        )
    }
}
