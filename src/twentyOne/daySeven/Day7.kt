package twentyOne.daySeven

import java.io.File
import kotlin.math.abs

fun main(args: Array<String>) {
    File("src/twentyOne/daySeven/input.txt").useLines { lines ->
        lines.forEach { line ->
            val positions = line.split(",").map { it.toInt() }
            val firstPos = positions.minOrNull()!!
            val lastPos = positions.maxOrNull()!!
            var min = Int.MAX_VALUE
            for (i in firstPos..lastPos) {
                var acc = 0
                for (pos in positions) {
                    // part one would be this:
                    // abs(pos - i)
                    acc += sumConsecutiveInts(abs(pos - i))
                }
                if (acc < min) min = acc
            }
            println(min)
        }
    }
}

fun sumConsecutiveInts(n: Int) = (n * (n + 1)) / 2