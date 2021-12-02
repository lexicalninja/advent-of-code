package twentyOne.dayTwo

import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {
    partOne()
    partTwo()
}

fun partOne() {
    val position = Position()
    File("src/twentyOne/dayTwo/input.txt").useLines { lines -> lines.forEach { position.changePos(it) } }
    println(abs(position.x * position.y))
}

fun partTwo() {
    val position = PositionWithAim()
    File("src/twentyOne/dayTwo/input.txt").useLines { lines -> lines.forEach { position.changePos(it) } }
    println(position.x * position.y)
}

data class Position(var x: Int = 0, var y: Int = 0) {
    fun changePos(command: String) {
        command.split(" ").apply {
            when (first()) {
                "forward" -> x += last().toInt()
                "up" -> y += last().toInt()
                "down" -> y -= last().toInt()
            }
        }
    }
}

data class PositionWithAim(var x: Int = 0, var y: Int = 0, var aim: Int = 0) {
    fun changePos(command: String) {
        command.split(" ").apply {
            when (first()) {
                "forward" -> {
                    x += last().toInt()
                    y += last().toInt() * aim
                }
                "up" -> aim -= last().toInt()
                "down" -> aim += last().toInt()
            }
        }
    }
}