package twentyOne.dayThree

import java.io.File
import kotlin.math.ceil
import kotlin.math.floor

fun main(args: Array<String>) {
    partOne()
    partTwo()
}

fun partOne() {
    val transposed = mutableListOf(mutableListOf<Int>())
    var counter = 0
    File("src/twentyOne/dayThree/input.txt").useLines { lines ->
        lines.forEach {
            it.forEachIndexed { i, c ->
                val n = Character.getNumericValue(c)
                transposed.getOrNull(i)?.apply { this.add(counter, n) } ?: run { transposed.add(i, mutableListOf(n)) }
            }
            counter++
        }
    }
    val half = floor(counter / 2.0)
    val gamma = transposed.map { if (it.reduce { acc, i -> acc + i } > half) 1 else 0 }
    val epsilon = gamma.map { if (it == 1) 0 else 1 }
    println(transposed)
    println(gamma.joinToString("").toInt(2) * epsilon.joinToString("").toInt(2))

}

fun partTwo() {
    val bits = mutableListOf<String>()
    File("src/twentyOne/dayThree/input.txt").useLines { lines -> lines.forEach { bits.add(it) } }
    val answer = getCo2ScrubberRating(bits, 0, 12) * getOxGeneratorRating(bits, 0, 12)
    println(answer)
}

fun getOxGeneratorRating(arr: MutableList<String>, bitPos: Int, max: Int): UInt {
    if (arr.size == 1) return arr.first().toUInt(2)
    val bitsSet = arr.sumOf { Character.getNumericValue(it[bitPos]) }
    val greaterThan = bitsSet >= ceil(arr.size / 2.0)
    val remaining = mutableListOf<String>()
    val shift = 11 - bitPos
    val midpoint = 0x1.toUInt() shl shift
    val bitMask = '1'.repeat(shift + 1).toUInt(2)
    arr.forEach {
        val bits = it.toUInt(2)
        if (greaterThan) {
            if ((bits and bitMask) >= midpoint) remaining.add(it)
        } else {
            if ((bits and bitMask) < midpoint) remaining.add(it)
        }
    }
    return getOxGeneratorRating(remaining, bitPos + 1, max)
}

fun getCo2ScrubberRating(arr: MutableList<String>, bitPos: Int, max: Int): UInt {
    if (arr.size == 1) return arr.first().toUInt(2)
    val bitsSet = arr.sumOf { Character.getNumericValue(it[bitPos]) }
    val lessThan = bitsSet >= ceil(arr.size / 2.0)
    val remaining = mutableListOf<String>()
    val shift = 11 - bitPos
    val midpoint = 0x1.toUInt() shl shift
    val bitMask = '1'.repeat(shift + 1).toUInt(2)
    arr.forEach {
        val bits = it.toUInt(2)
        if (lessThan) {
            if ((bits and bitMask) < midpoint) remaining.add(it)
        } else {
            if ((bits and bitMask) >= midpoint) remaining.add(it)
        }
    }
    return getCo2ScrubberRating(remaining, bitPos + 1, max)
}

fun Char.repeat(count: Int): String = this.toString().repeat(count)
