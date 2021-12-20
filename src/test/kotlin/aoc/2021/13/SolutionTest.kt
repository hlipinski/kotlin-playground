package aoc.`2021`.`13`

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SolutionTest {

    private val solution = Solution("test_input.txt")

    @Test
    fun shouldSolve() {
        assertThat(solution.solve()).isEqualTo(17)
    }

    @Test
    fun shouldFoldCorrectly() {
        assertThat(solution.fold(solution.dots, solution.folds[0])).isEqualTo(hashSetOf(
                "0|0", "0|1", "0|3",
                "1|4",
                "2|0",
                "3|0", "3|4",
                "4|1", "4|3",
                "6|0", "6|2", "6|4",
                "8|4",
                "9|0", "9|4",
                "10|2", "10|4"
        ))
    }
}
