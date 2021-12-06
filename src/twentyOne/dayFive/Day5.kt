package twentyOne.dayFive

import java.awt.Point
import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    partOne()
}

fun partOne() {
    val array = MutableList(1000) { MutableList(1000) { 0 } }
    val segments = mutableListOf<LineSegment>()
    File("src/twentyOne/dayFive/input.txt").useLines { lines ->
        lines.forEach {
            val a = it.split(" -> ")
            val b = a.map { sub -> sub.split(",").toPoint() }
            val newSegment = LineSegment(b[0], b[1])
            if(newSegment.isVertical() || newSegment.isHorizontal()){
                for (segment in segments) {
                    val intersects = newSegment.intersectsWith(segment)
                    intersects?.apply {
                        this.forEach { pair ->
                            array[pair.second][pair.first] = array[pair.second][pair.first] + 1
                        }
                    }
                }
                segments.add(newSegment)
            }
        }
    }
//    val answer = array.map { it.reduce{ acc: Int, i: Int -> if(i > 0) acc + 1 else 0 } }.sum()
    val answer = array.sumOf { it.sum() }
    println(answer)
}

class LineSegment(val a: Point, val b: Point) {
    fun intersectsWith(other: LineSegment): List<Pair<Int, Int>>? {
        val dx1 = b.x - a.x
        val dx2 = other.b.x - other.a.x
        val dy1 = b.y - a.y
        val dy2 = other.b.y - other.a.y
        val dx3 = a.x - other.a.x
        val dy3 = a.y - other.b.y
        val det = dx1 * dy2 - dx2 * dy1
        val det1 = dx1 * dy3 - dx3 * dy1
        val det2 = dx2 * dy3 - dx3 * dy2
        if (det == 0) {
            //parallel, need to check for colinear
            if (det1 == 0 || det2 == 0) {
//                //colinear, check for overlap.
                if (dx1 == 0 ) {
                    val range1 = (min(a.y, b.y)..max(a.y,b.y)).toSet()
                    val range2 = (min(other.a.y, other.b.y)..max(other.a.y, other.b.y)).toSet()
                    val intersect = range1.intersect(range2)
                    return intersect.map { Pair(a.x, it) }
                }
                if (dy1 == 0 ) {
                    val range1 = (min(a.x, b.x)..max(a.x, b.x)).toSet()
                    val range2 = (min(other.a.x, other.b.x)..max(other.a.x, other.b.x)).toSet()
                    val intersect = range1.intersect(range2)
                    return intersect.map { Pair(it, a.y) }
                }
            } else return null
        }
        if(dx1 == 0){
            val rangeY = min(a.y, b.y)..max(a.y,b.y)
            val rangeX = min(other.a.x, other.b.x)..max(other.a.x, other.b.x)
            if(a.x in rangeX && other.a.y in rangeY) {
                return listOf(Pair(a.x, other.a.y))
            }
        }
        if(dy1 == 0) {
            val rangeY = min(other.a.y, other.b.y)..max(other.a.y,other.b.y)
            val rangeX = min(a.x, b.x)..max(a.x, b.x)
            if(a.y in rangeY && other.a.x in rangeX) {
                return listOf(Pair(other.a.x, a.y))
            }
        }
        return null
    }

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

}

fun List<String>.toPoint(): Point {
    if (this.size != 2) {
        throw IllegalArgumentException("List is not of length 2!")
    }
    return Point(this[0].toInt(), this[1].toInt())
}