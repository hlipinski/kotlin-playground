package aoc.`2021`.`11`

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Solution2Test {
    private val solution = Solution2("test_input.txt")

    @Test
    fun shouldSolve() {
        assertThat(solution.solve()).isEqualTo(195)
    }
}

class SolutionTest {

    private val solution = Solution("test_input.txt")

    @Test
    fun shouldSolve() {
        assertThat(solution.solve()).isEqualTo(1656)
    }

    @Test
    fun shouldFindNeighbours() {
        assertThat(solution.getNeighbours("0|0")).isEqualTo(hashSetOf("0|1", "1|0", "1|1"))
        assertThat(solution.getNeighbours("2|2")).isEqualTo(hashSetOf("1|1","2|1", "3|1", "1|2", "3|2", "1|3", "2|3", "3|3"))
        assertThat(solution.getNeighbours("2|9")).isEqualTo(hashSetOf("1|9", "1|8", "2|8", "3|8", "3|9"))
        assertThat(solution.getNeighbours("9|9")).isEqualTo(hashSetOf("8|9", "9|8", "8|8"))
    }
}
