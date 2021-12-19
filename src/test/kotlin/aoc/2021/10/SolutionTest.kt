package aoc.`2021`.`10`

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Solution2Test {

    private val solution = Solution2("test_input.txt")

    @Test
    fun shouldSolve() {
        assertThat(solution.solve()).isEqualTo(288957)
    }

    @Test
    fun shouldFindMissingChars() {
        assertThat(solution.findMissingChars("[({(<(())[]>[[{[]{<()<>>")).isEqualTo("}}]])})]")
        assertThat(solution.findMissingChars("[(()[<>])]({[<{<<[]>>(")).isEqualTo(")}>]})")
        assertThat(solution.findMissingChars("(((({<>}<{<{<>}{[]{[]{}")).isEqualTo("}}>}>))))")
        assertThat(solution.findMissingChars("{<[[]]>}<{[{[{[]{()[[[]")).isEqualTo("]]}}]}]}>")
        assertThat(solution.findMissingChars("<{([{{}}[<[[[<>{}]]]>[]]")).isEqualTo("])}>")
    }

    @Test
    fun shouldCalculateScoreProperly() {
        assertThat(solution.calculateScoreForLine("}}]])})]")).isEqualTo(288957)
        assertThat(solution.calculateScoreForLine(")}>]})")).isEqualTo(5566)
        assertThat(solution.calculateScoreForLine("}}>}>))))")).isEqualTo(1480781)
        assertThat(solution.calculateScoreForLine("]]}}]}]}>")).isEqualTo(995444)
        assertThat(solution.calculateScoreForLine("])}>")).isEqualTo(294)
    }
}

class SolutionTest {

    private val solution = Solution("test_input.txt")

    @Test
    fun shouldSolve() {
        assertThat(solution.solve()).isEqualTo(26397)
    }

    @Test
    fun shouldFindIncorrectChar() {
        assertThat(solution.findFirstIncorrectChar("{([(<{}[<>[]}>{[]{[(<()>")).isEqualTo("}")
        assertThat(solution.findFirstIncorrectChar("[[<[([]))<([[{}[[()]]]")).isEqualTo(")")
        assertThat(solution.findFirstIncorrectChar("[{[{({}]{}}([{[{{{}}([]")).isEqualTo("]")
        assertThat(solution.findFirstIncorrectChar("[<(<(<(<{}))><([]([]()")).isEqualTo(")")
        assertThat(solution.findFirstIncorrectChar("<{([([[(<>()){}]>(<<{{")).isEqualTo(">")
    }
}
