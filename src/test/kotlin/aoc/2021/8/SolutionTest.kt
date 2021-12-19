package aoc.`2021`.`8`

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Solution2Test {
    private val solution = Solution2("test_input.txt")

    @Test
    fun shouldSolve() {
        assertThat(solution.solve()).isEqualTo(61229)
    }

    @Test
    fun shouldDecodeCorrectly() {
        val pair = solution.parseLineWithDictionary("be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe")
        assertThat(solution.decode(pair.first, pair.second))
                .isEqualTo(8394)
    }
}

class SolutionTest {

    private val solution = Solution("test_input.txt")

    @Test
    fun shouldSolve() {
        assertThat(solution.solve()).isEqualTo(26)
    }

    @Test
    fun shouldParseLineCorrectly() {
        assertThat(solution.parseLine("be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe"))
                .isEqualTo(listOf("fdgacbe","cefdb","cefbgd","gcbe"))
    }
}
