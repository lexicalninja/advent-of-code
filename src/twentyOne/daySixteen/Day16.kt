package twentyOne.daySixteen

import com.sun.tools.corba.se.idl.InvalidArgument
import twentyOne.dayFourteen.splitIgnoreEmpty
import java.io.File

val CONVERTER = mapOf(
  '0' to "0000",
  '1' to "0001",
  '2' to "0010",
  '3' to "0011",
  '4' to "0100",
  '5' to "0101",
  '6' to "0110",
  '7' to "0111",
  '8' to "1000",
  '9' to "1001",
  'A' to "1010",
  'B' to "1011",
  'C' to "1100",
  'D' to "1101",
  'E' to "1110",
  'F' to "1111"
)

@OptIn(ExperimentalUnsignedTypes::class) fun main(args: Array<String>) {

  val builder = StringBuilder()
  File("src/twentyOne/daySixteen/input.txt").useLines { lines ->
    lines.first().forEach { builder.append(CONVERTER[it]) }
  }

  val p = Packet(builder.toString().splitIgnoreEmpty(""))
  println(p.getValue())
//  println(parseUIntPacketVersions(builder.toString().splitIgnoreEmpty("")))

}

fun String.hexToUInt(): ULong = this.toLong(16).toULong()

@OptIn(ExperimentalUnsignedTypes::class) fun String.hexToUBytArray(): UByteArray {
  return Integer.parseInt(this, 16).toUInt().toUByteArray()
}

@OptIn(ExperimentalUnsignedTypes::class) fun UInt.toUByteArray(): UByteArray {
  return UByteArray(4).also {
    it[0] = (this shr 0).toUByte()
    it[1] = (this shr 8).toUByte()
    it[2] = (this shr 16).toUByte()
    it[3] = (this shr 24).toUByte()
  }
}


data class Packet(val bits: List<String>) {
  val LiteralCode = "100"
  val version = bits.take(3).joinToString("")
  val type = bits.subList(3, 6).joinToString("")
  // todo: update this
  val subPackets = bits.drop(6)
  val isLiteral = type == LiteralCode
  val lengthType = bits[6]

  fun getValue(): Pair<Long, Int>? {
    if (!isLiteral) return null
    val result = StringBuilder()
    val b = subPackets.toMutableList()
    var length = 0
    while (b.isNotEmpty()){
      val lead = b.chop().first()
      result.append(b.chop(4).joinToString(""))
      length += 5
      if (lead == "0") break
    }
    return Pair(result.toString().toLong(2), length + 6)
  }





}

fun <T>MutableList<T>.chop(num: Int = 1): MutableList<T> {
  if(num < 0) throw InvalidArgument("value must be positive")
  val result = mutableListOf<T>()
  for(i in 1..num) {
    result.add(this.removeAt(0))
  }
  return result
}


