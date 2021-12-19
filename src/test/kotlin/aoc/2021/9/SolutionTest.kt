package aoc.`2021`.`9`

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Solution2Test {
    private val solution = Solution2("test_input.txt")

    @Test
    fun shouldSolve() {
        assertThat(solution.solve()).isEqualTo(1134)
    }

    @Test
    fun shouldCountBasinCorrectly() {
        assertThat(solution.countBasinSize("0|1")).isEqualTo(3)
        assertThat(solution.countBasinSize("0|9")).isEqualTo(9)
        assertThat(solution.countBasinSize("2|2")).isEqualTo(14)
        assertThat(solution.countBasinSize("4|6")).isEqualTo(9)
    }
}

class SolutionTest {

    private val solution = Solution("test_input.txt")

    @Test
    fun shouldSolve() {
        assertThat(solution.solve()).isEqualTo(15)
    }

    @Test
    fun shouldFindNeighbours() {
        assertThat(solution.getNeighbours("0|0")).isEqualTo(hashSetOf("0|1", "1|0"))
        assertThat(solution.getNeighbours("2|2")).isEqualTo(hashSetOf("2|1", "1|2", "2|3", "3|2"))
        assertThat(solution.getNeighbours("2|9")).isEqualTo(hashSetOf("1|9", "2|8", "3|9"))
        assertThat(solution.getNeighbours("4|9")).isEqualTo(hashSetOf("3|9", "4|8"))
    }
}
