package twentyOne.dayOne

import java.io.File

fun main(args: Array<String>) {
    partOne()
   partTwo()
}

fun partOne() {
    val depths = mutableListOf<Int>()
    File("src/twentyOne/dayOne/input.txt").useLines { lines -> lines.forEach { depths.add(it.toInt()) }}
    var count = 0
    depths.forEachIndexed { index, i -> if (index > 0 && depths[index - 1] < i) count++ }
    println(count)
}

fun partTwo() {
    val depths = mutableListOf<Int>()
    var count = 0
    File("src/twentyOne/dayOne/input.txt").useLines { lines -> lines.forEach { depths.add(it.toInt()) }}
    val windows = depths.windowed(3, 1, false).map { it.sum() }
    windows.forEachIndexed { index, i -> if (index > 0 && windows[index - 1] < i) count++ }
    println(count)
}