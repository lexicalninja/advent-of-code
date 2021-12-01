package twentyOne.dayOne

import twentyOne.listFromTxt
import java.io.File

fun main(args: Array<String>) {
    partOne()
    partTwo()
}

fun partOne() {
    val depths = File("src/twentyOne/dayOne/input.txt").listFromTxt { it.toInt() }
    var count = 0
    depths.forEachIndexed { index, i -> if (index > 0 && depths[index - 1] < i) count++ }
    println(count)
}

fun partTwo() {
    val depths = File("src/twentyOne/dayOne/input.txt").listFromTxt { it.toInt() }
    var count = 0
    val windows = depths.windowed(3, 1, false) { it.sum() }
    // ALTERNATE
//    val count = depths.windowed(3, 1, false) { it.sum() }.zipWithNext { a, b -> if(b > a) 1 else 0 }.sum()
    windows.forEachIndexed { index, i -> if (index > 0 && windows[index - 1] < i) count++ }
    println(count)
}