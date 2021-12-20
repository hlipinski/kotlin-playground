package aoc.`2021`.`14`

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigInteger

class Solution2Test {
    private val solution = Solution2("test_input.txt")

    @Test
    fun shouldSolve() {
        assertThat(solution.solve()).isEqualTo(2188189693529)
    }
}

class SolutionTest {
    private val solution = Solution("test_input.txt")

    @Test
    fun shouldSolve() {
        assertThat(solution.solve()).isEqualTo(1588)
    }

    @Test
    fun shouldProcessStepCorrectly() {
        assertThat(solution.step(listOf("N","N","C","B"))).containsExactly("N","C","N","B","C","H","B")
        assertThat(solution.step(listOf("N","C","N","B","C","H","B"))).containsExactly("N","B","C","C","N","B","B","B","C","B","H","C","B")
    }
}
