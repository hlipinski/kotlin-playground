package aoc.`2021`.`16`

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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
    fun shouldConvertToPacket_Example_1() {
        assertThat((solution.countVersions("8A004A801A8002F478"))).isEqualTo(16)
    }

    @Test
    fun shouldConvertToPacket_Example_2() {
        assertThat((solution.countVersions("620080001611562C8802118E34"))).isEqualTo(12)
    }

    @Test
    fun shouldConvertToPacket_Example_3() {
        assertThat((solution.countVersions("C0015000016115A2E0802F182340"))).isEqualTo(23)
    }
}
