package aoc.`2021`.`12`

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Solution2Test {
    private val solution = Solution2("test_input_small.txt")

    @Test
    fun shouldSolve() {
        assertThat(Solution2("test_input_large.txt").solve()).isEqualTo(3509)
    }

    @Test
    fun shouldFindAllPaths_Small() {
        assertThat(solution.findAllPaths()).isEqualTo(hashSetOf(
                "start,A,b,A,b,A,c,A,end",
                "start,A,b,A,b,A,end",
                "start,A,b,A,b,end",
                "start,A,b,A,c,A,b,A,end",
                "start,A,b,A,c,A,b,end",
                "start,A,b,A,c,A,c,A,end",
                "start,A,b,A,c,A,end",
                "start,A,b,A,end",
                "start,A,b,d,b,A,c,A,end",
                "start,A,b,d,b,A,end",
                "start,A,b,d,b,end",
                "start,A,b,end",
                "start,A,c,A,b,A,b,A,end",
                "start,A,c,A,b,A,b,end",
                "start,A,c,A,b,A,c,A,end",
                "start,A,c,A,b,A,end",
                "start,A,c,A,b,d,b,A,end",
                "start,A,c,A,b,d,b,end",
                "start,A,c,A,b,end",
                "start,A,c,A,c,A,b,A,end",
                "start,A,c,A,c,A,b,end",
                "start,A,c,A,c,A,end",
                "start,A,c,A,end",
                "start,A,end",
                "start,b,A,b,A,c,A,end",
                "start,b,A,b,A,end",
                "start,b,A,b,end",
                "start,b,A,c,A,b,A,end",
                "start,b,A,c,A,b,end",
                "start,b,A,c,A,c,A,end",
                "start,b,A,c,A,end",
                "start,b,A,end",
                "start,b,d,b,A,c,A,end",
                "start,b,d,b,A,end",
                "start,b,d,b,end",
                "start,b,end"
        ))
    }
}

class SolutionTest {

    private val solution = Solution("test_input_small.txt")

    @Test
    fun shouldSolve() {
        assertThat(Solution("test_input_large.txt").solve()).isEqualTo(226)
    }

    @Test
    fun shouldFindAllPaths_Small() {
        assertThat(solution.findAllPaths()).isEqualTo(hashSetOf(
                "start,A,b,A,c,A,end",
                "start,A,b,A,end",
                "start,A,b,end",
                "start,A,c,A,b,A,end",
                "start,A,c,A,b,end",
                "start,A,c,A,end",
                "start,A,end",
                "start,b,A,c,A,end",
                "start,b,A,end",
                "start,b,end"
        ))
    }

    @Test
    fun shouldFindAllPaths_Medium() {
        assertThat(Solution("test_input_med.txt").findAllPaths()).isEqualTo(hashSetOf(
                "start,HN,dc,HN,end",
                "start,HN,dc,HN,kj,HN,end",
                "start,HN,dc,end",
                "start,HN,dc,kj,HN,end",
                "start,HN,end",
                "start,HN,kj,HN,dc,HN,end",
                "start,HN,kj,HN,dc,end",
                "start,HN,kj,HN,end",
                "start,HN,kj,dc,HN,end",
                "start,HN,kj,dc,end",
                "start,dc,HN,end",
                "start,dc,HN,kj,HN,end",
                "start,dc,end",
                "start,dc,kj,HN,end",
                "start,kj,HN,dc,HN,end",
                "start,kj,HN,dc,end",
                "start,kj,HN,end",
                "start,kj,dc,HN,end",
                "start,kj,dc,end"
        ))
    }

    @Test
    fun shouldConvertFileProperly() {
        assertThat(solution.convertFile()).isEqualTo(hashMapOf(
                Pair("start", hashSetOf("A", "b")),
                Pair("end", hashSetOf("A", "b")),
                Pair("c", hashSetOf("A")),
                Pair("d", hashSetOf("b")),
                Pair("A", hashSetOf("start", "end", "b", "c")),
                Pair("b", hashSetOf("start", "end", "A", "d"))
        ))
    }
}
