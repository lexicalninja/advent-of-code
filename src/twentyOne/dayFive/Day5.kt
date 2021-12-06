package twentyOne.dayFive

import java.awt.Point
import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    partOne()
    partTwo()
}

fun partOne() {
    val map = MutableList(1000) { MutableList(1000) { 0 } }
    File("src/twentyOne/dayFive/input.txt").useLines { lines ->
        lines.forEach {
            val endPoints = it.split(" -> ").map { sub -> sub.split(",").toPoint() }
            val newSegment = LineSegment(endPoints[0], endPoints[1])
            markVents(newSegment, map)
        }
    }
    var count = 0
    map.forEach { count += it.filter { i -> i > 1 }.size }
    println(count)
}

fun partTwo() {
    val map = MutableList(1000) { MutableList(1000) { 0 } }
    File("src/twentyOne/dayFive/input.txt").useLines { lines ->
        lines.forEach {
            val endPoints = it.split(" -> ").map { sub -> sub.split(",").toPoint() }
            val newSegment = LineSegment(endPoints[0], endPoints[1])
            markVents(newSegment, map, true)
        }
    }
    var count = 0
    map.forEach { count += it.filter { i -> i > 1 }.size }
    println(count)
}

fun markVents(segment: LineSegment, array: MutableList<MutableList<Int>>, useDiagonal: Boolean = false) {
    return when {
        segment.isVertical() -> {
            val x = segment.a.x
            for (y in min(segment.a.y, segment.b.y)..max(segment.a.y, segment.b.y)) {
                array[y][x] = array[y][x] + 1
            }
        }
        segment.isHorizontal() -> {
            val y = segment.a.y
            for (x in min(segment.a.x, segment.b.x)..max(segment.a.x, segment.b.x)) {
                array[y][x] = array[y][x] + 1
            }
        }
        useDiagonal && segment.is45Degrees() -> {
            val m = segment.getSlope()
            val b = segment.a.y - segment.a.x * m
            val rangeX = min(segment.a.x, segment.b.x)..max(segment.a.x, segment.b.x)
            for (x in rangeX) {
                val y = m * x + b
                array[y][x] = array[y][x] + 1
            }
        }
        else -> Unit
    }
}

class LineSegment(val a: Point, val b: Point) {
    companion object {
        fun determinant(a: LineSegment, b: LineSegment): Int {
            val dx1 = a.b.x - a.a.x
            val dx2 = b.b.x - b.a.x
            val dy1 = a.b.y - a.a.y
            val dy2 = b.b.y - b.a.y
            return dx1 * dy2 - dx2 * dy1
        }
    }

    fun isVertical() = a.x == b.x
    fun isHorizontal() = a.y == b.y
    fun is45Degrees() = abs(getSlope()) == 1
    fun getSlope() = (b.y - a.y) / (b.x - a.x)

}

fun <T> print2dArray(a: MutableList<MutableList<T>>): String {
    var result = ""
    a.forEach { result += "$it\n" }
    return result
}

fun List<String>.toPoint(): Point {
    if (this.size != 2) {
        throw IllegalArgumentException("List is not of length 2!")
    }
    return Point(this[0].toInt(), this[1].toInt())
}

