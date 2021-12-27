package aoc.`2021`.`16`

import java.io.File

fun main(args: Array<String>) {
    println("Part 1: ${Solution("input.txt").solve()}")
//    println("Part 2: ${Solution2("input.txt").solve()}")
}

open class Solution(fileName: String) {

    val file = File("aoc/2021/16", fileName)
    val dict = createDict()

    open fun solve(): Int = countVersions(file.readLines()[0])

    fun countVersions(message: String): Int {
        val packet = convertPackets(message)
        return sumVersions(packet)
    }

    fun convertPackets(message: String): Packet {
        val binary = convertToBinary(message)
        return PacketFactory.convertToPacket(binary)
    }

    fun sumVersions(packet: Packet): Int {
        if (packet is LiteralPacket) return packet.version
        else if (packet is OperatorPacket) return packet.version + packet.packets.sumBy { sumVersions(it) }
        return -1
    }

    fun convertToBinary(message: String): String = message.map { dict[it] }.joinToString("")

    private fun createDict() =
            hashMapOf(
                    Pair('0', "0000"), Pair('1', "0001"), Pair('2', "0010"), Pair('3', "0011"),
                    Pair('4', "0100"), Pair('5', "0101"), Pair('6', "0110"), Pair('7', "0111"),
                    Pair('8', "1000"), Pair('9', "1001"), Pair('A', "1010"), Pair('B', "1011"),
                    Pair('C', "1100"), Pair('D', "1101"), Pair('E', "1110"), Pair('F', "1111")
            )
}

data class LiteralPacket(override val version: Int, val type: Int, val number: Long, override var length: Int, override var rest: String?) : Packet(version, type, length, rest)
data class OperatorPacket(override val version: Int, val type: Int, val lengthType: Int, val packets: List<Packet>, override var length: Int, override var rest: String?) : Packet(version, type, length, rest)

open class Packet(open val version: Int, type: Int, open var length: Int, open var rest: String?)

object PacketFactory {

    fun convertToPacket(message: String): Packet {
        val packet = convertToSubpacket(message)

        if (packet.length % 4 > 0) {
            var additionalLength = 4 - packet.length % 4
            var rest = packet.rest!!.substring(0, additionalLength)

            if (packet.rest!!.substring(additionalLength).startsWith("0000")) {
                additionalLength += 4
                rest = packet.rest!!.substring(0, additionalLength)
            }
            packet.length += additionalLength
            packet.rest = rest
        }

        return packet
    }

    private fun convertToSubpacket(message: String): Packet {
        val version = message.substring(IntRange(0, 2))
        val type = message.substring(IntRange(3, 5))
        var packet: Packet

        if (type.isLiteral()) {
            packet = literal(version, type, message.substring(6))
        } else {
            packet = operator(version, type, message.substring(6))
        }

        return packet
    }

    private fun String.isLiteral() = this.toInt(2) == 4

    private fun literal(version: String, type: String, number: String): Packet {
        val list = mutableListOf<String>()
        var partLength = 0

        while (true) {
            val numPart = number.substring(partLength, partLength + 5)
            partLength += 5

            list.add(numPart.substring(1))
            if (numPart.startsWith("0")) break
        }

        val length = (version + type).length + partLength

        val rest = number.substring(partLength)
        val versionInt = version.toInt(2)
        val typeInt = type.toInt(2)
        val literalValue = list.joinToString("").toLong(2)

        return LiteralPacket(versionInt, typeInt, literalValue, length, rest)
    }

    private fun operator(version: String, type: String, number: String): Packet {
        val lengthType = number.substring(0, 1).toInt()

        val packets: MutableList<Packet> = mutableListOf()

        if (lengthType == 0) {
            val subPacketsLength = number.substring(1, 16).toInt(2)

            var subPacketsMessage = number.substring(16, 16 + subPacketsLength)

            var result = convertToSubpacket(subPacketsMessage)
            packets.add(result)

            var counter = result.length
            while (counter < subPacketsLength) {
                subPacketsMessage = subPacketsMessage.substring(result.length)
                result = convertToSubpacket(subPacketsMessage)
                packets.add(result)
                counter += result.length
            }

            val length = (version + type).length + 16 + subPacketsLength
            val rest = number.substring(16 + subPacketsLength)

            return OperatorPacket(
                    version.toInt(2),
                    type.toInt(2),
                    lengthType,
                    packets,
                    length,
                    rest)
        } else {
            val subPacketsCount = number.substring(1, 12).toInt(2)
            var subPacketsMessage = number.substring(12)
            val subPackets = mutableListOf<Packet>()
            IntRange(0, subPacketsCount - 1).forEach {
                val result = convertToSubpacket(subPacketsMessage)
                subPacketsMessage = subPacketsMessage.substring(result.length)
                subPackets.add(result)
            }

            val length = (version + type).length + 12 + subPackets.sumBy { it.length }

            packets.addAll(subPackets)
            return OperatorPacket(
                    version.toInt(2),
                    type.toInt(2),
                    lengthType,
                    packets,
                    length,
                    subPacketsMessage
            )
        }
    }
}
