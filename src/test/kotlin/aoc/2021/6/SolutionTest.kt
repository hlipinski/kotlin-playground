package aoc.`2021`.`6`

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigInteger

class SolutionTest {

    private val solution = Solution("test_input.txt")

    @Test
    fun shouldSolve() {
        assertThat(solution.solve(18)).isEqualTo(26);
        assertThat(solution.solve(80)).isEqualTo(5934);
        assertThat(solution.solve(256)).isEqualTo(26984457539);
    }

    @Test
    fun shouldConvertToSet() {
        assertThat(solution.convertFile()).isEqualTo(
                hashMapOf(Pair(1, BigInteger.ONE), Pair(2, BigInteger.ONE), Pair(3, BigInteger.valueOf(2)), Pair(4, BigInteger.ONE))
        )
    }
}
