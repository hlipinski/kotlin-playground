package aoc.`2021`.`4`

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Solution2Test {

    private val solution2 = Solution2("test_input.txt")

    @Test
    fun shouldSolve() {
        assertThat(solution2.solve()).isEqualTo(1924);
    }
}

class Solution1Test {

    private val solution1 = Solution1("test_input.txt")

    @Test
    fun shouldSolve() {
        assertThat(solution1.solve()).isEqualTo(4512);
    }
}

class SolutionTest {

    private val solution1 = Solution1("test_input.txt")

    @Test
    fun shouldConvertToMatrixesCorrectly() {
        assertThat(solution1.uploadFile().size).isEqualTo(3)
        assertThat(solution1.uploadFile()[0].rows).containsExactlyInAnyOrder(
                hashSetOf(22, 13, 17, 11, 0),
                hashSetOf(8, 2, 23, 4, 24),
                hashSetOf(21, 9, 14, 16, 7),
                hashSetOf(6, 10, 3, 18, 5),
                hashSetOf(1, 12, 20, 15, 19)
        )
    }

    @Test
    fun shouldConvertRowsCorrectly() {
        assertThat(solution1.uploadFile()[0].rows).containsExactlyInAnyOrder(
                hashSetOf(22, 13, 17, 11, 0),
                hashSetOf(8, 2, 23, 4, 24),
                hashSetOf(21, 9, 14, 16, 7),
                hashSetOf(6, 10, 3, 18, 5),
                hashSetOf(1, 12, 20, 15, 19)
        )
    }

    @Test
    fun shouldConvertColumnsCorrectly() {
        assertThat(solution1.uploadFile()[0].columns).containsExactlyInAnyOrder(
                hashSetOf(22, 8, 21, 6, 1),
                hashSetOf(13, 2, 9, 10, 12),
                hashSetOf(17, 23, 14, 3, 20),
                hashSetOf(11, 4, 16, 18, 15),
                hashSetOf(0, 24, 7, 5, 19)
        )
    }
}
