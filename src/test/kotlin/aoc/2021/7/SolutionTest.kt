package aoc.`2021`.`7`

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Solution2Test {

    private val solution = Solution2("test_input.txt")

    @Test
    fun shouldSolve() {
        assertThat(solution.solve()).isEqualTo(168)
    }

    @Test
    fun shouldCalculateFuelCorrectly() {
        assertThat(solution.calculateFuel(2)).isEqualTo(206)
        assertThat(solution.calculateFuel(5)).isEqualTo(168)
    }
}

class SolutionTest {

    private val solution = Solution("test_input.txt")

    @Test
    fun shouldSolve() {
        assertThat(solution.solve()).isEqualTo(37)
    }

    @Test
    fun shouldCalculateFuelCorrectly() {
        assertThat(solution.calculateFuel(1)).isEqualTo(41)
        assertThat(solution.calculateFuel(2)).isEqualTo(37)
        assertThat(solution.calculateFuel(3)).isEqualTo(39)
        assertThat(solution.calculateFuel(10)).isEqualTo(71)
    }
}
