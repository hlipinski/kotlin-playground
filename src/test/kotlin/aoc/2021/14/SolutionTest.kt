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
    fun shouldCountLettersCorrectly() {
        var map = solution.polymer
        map = solution.step(map)
        solution.step(map)

        assertThat(solution.lettersMap).isEqualTo(hashMapOf(Pair("N", 2), Pair("C", 4), Pair("B", 6), Pair("H", 1)))
    }

    @Test
    fun shouldProcessStepCorrectly() {
        assertThat(solution.step(
                hashMapOf(Pair("NN", BigInteger.ONE), Pair("NC", BigInteger.ONE), Pair("CB", BigInteger.ONE)))
        ).isEqualTo(
                hashMapOf(
                        Pair("NC", BigInteger.ONE), Pair("CN", BigInteger.ONE), Pair("NB", BigInteger.ONE),
                        Pair("BC", BigInteger.ONE), Pair("CH", BigInteger.ONE), Pair("HB", BigInteger.ONE)
                )
        )
        assertThat(solution.step(
                hashMapOf(
                        Pair("NC", BigInteger.ONE), Pair("CN", BigInteger.ONE), Pair("NB", BigInteger.ONE),
                        Pair("BC", BigInteger.ONE), Pair("CH", BigInteger.ONE), Pair("HB", BigInteger.ONE)
                )
        )).isEqualTo(
                hashMapOf(
                        Pair("NB", BigInteger.TWO), Pair("BC", BigInteger.TWO), Pair("CC", BigInteger.ONE),
                        Pair("CN", BigInteger.ONE), Pair("BB", BigInteger.TWO), Pair("CB", BigInteger.TWO),
                        Pair("BH", BigInteger.ONE), Pair("HC", BigInteger.ONE)
                )
        )
    }
}
