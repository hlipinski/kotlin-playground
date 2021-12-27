package aoc.`2021`.`16`

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Solution2Test {
    private val solution = Solution2("test_input.txt")

    @Test
    fun shouldEvaluate_sum() {
        assertThat(solution.calculate("C200B40A82")).isEqualTo(3)
    }

    @Test
    fun shouldEvaluate_product() {
        assertThat(solution.calculate("04005AC33890")).isEqualTo(54)
    }

    @Test
    fun shouldEvaluate_min() {
        assertThat(solution.calculate("880086C3E88112")).isEqualTo(7)
    }

    @Test
    fun shouldEvaluate_max() {
        assertThat(solution.calculate("CE00C43D881120")).isEqualTo(9)
    }

    @Test
    fun shouldEvaluate_lt() {
        assertThat(solution.calculate("D8005AC2A8F0")).isEqualTo(1)
    }

    @Test
    fun shouldEvaluate_gt() {
        assertThat(solution.calculate("F600BC2D8F")).isEqualTo(0)
    }

    @Test
    fun shouldEvaluate_eq() {
        assertThat(solution.calculate("9C005AC2F8F0")).isEqualTo(0)
    }

    @Test
    fun shouldEvaluate_complex() {
        assertThat(solution.calculate("9C0141080250320F1802104A08")).isEqualTo(1)
    }
}

class SolutionTest {
    private val solution = Solution("test_input.txt")

    @Test
    fun shouldSolve() {
        assertThat(solution.solve()).isEqualTo(31)
    }

    @Test
    fun shouldConvertToBinary() {
        assertThat(solution.convertToBinary("EE00D40C823060"))
                .isEqualTo("11101110000000001101010000001100100000100011000001100000")
    }

    @Test
    fun shouldConvertToPacket_Literal() {
        assertThat(PacketFactory.convertToPacket("110100101111111000101000"))
                .isEqualTo(
                        LiteralPacket(6, 4, 2021, 24, "000")
                )
    }

    @Test
    fun shouldConvertToPacket_Operator_0() {
        assertThat(PacketFactory.convertToPacket("00111000000000000110111101000101001010010001001000000000"))
                .isEqualTo(
                        OperatorPacket(1, 6, 0,
                                listOf<Packet>(
                                        LiteralPacket(6, 4, 10, 11, "0101001000100100"),
                                        LiteralPacket(2, 4, 20, 16, "")
                                ), 56, "0000000")
                )
    }

    @Test
    fun shouldConvertToPacket_Operator_1() {
        assertThat(PacketFactory.convertToPacket("11101110000000001101010000001100100000100011000001100000"))
                .isEqualTo(
                        OperatorPacket(7, 3, 1,
                                listOf(
                                        LiteralPacket(2, 4, 1, 11, "100100000100011000001100000"),
                                        LiteralPacket(4, 4, 2, 11, "0011000001100000"),
                                        LiteralPacket(1, 4, 3, 11, "00000")
                                ),
                                56, "00000")
                )
    }

    @Test
    fun shouldCalculate_Example_1() {
        assertThat((solution.calculate("8A004A801A8002F478"))).isEqualTo(16)
    }

    @Test
    fun shouldCalculate_Example_2() {
        assertThat((solution.calculate("620080001611562C8802118E34"))).isEqualTo(12)
    }

    @Test
    fun shouldCalculate_Example_3() {
        assertThat((solution.calculate("C0015000016115A2E0802F182340"))).isEqualTo(23)
    }
}
