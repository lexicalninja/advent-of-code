package twentyOne.dayEight

import java.io.File

fun main(args: Array<String>) {
//    partOne()
    partTwo()
}
fun partOne() {
    var unique = 0
    File("src/twentyOne/dayEight/input.txt").useLines { lines ->
        lines.forEach {
            it.split(" | ").last().split(" ").forEach {s ->
                when(s.length) {
                    2, 3, 4, 7  -> unique++
                }
            }
        }
    }
    println(unique)
}

fun partTwo() {
    var total = 0
    File("src/twentyOne/dayEight/input.txt").useLines { lines ->
        lines.forEach {
//            split line into the two parts we need
            val inOut = it.split(" | ")
//            make a map for O(1) lookup
            val map = mutableMapOf<String, Int> ()
//            sets for the strings we can't immediately map to a number
            val fives = mutableSetOf<String>()
            val sixes = mutableSetOf<String>()
//            create sets for the chars of numbers we can identify by total segments
            val one = mutableSetOf<Char>()
            val four = mutableSetOf<Char>()
            val seven = mutableSetOf<Char>()
            inOut.first().split(" ").forEach { string ->
//                sort the string for easier lookup
                val key = string.toList().sorted().joinToString("")
                when(string.length) {
                    2 -> {
                        map[key] = 1
                        one.addAll(key.toList())
                    }
                    3 -> {
                        map[key] = 7
                        seven.addAll(key.toList())
                    }
                    4 -> {
                        map[key] = 4
                        four.addAll(key.toList())
                    }
                    5 -> fives.add(key)
                    6 -> sixes.add(key)
                    7 -> {
                        map[key] = 8
                    }
                }
            }
            sixes.forEach { six ->
                val s = six.toSet()
                if (s.containsAll(seven) && (four subtract s).isNotEmpty()) map[six] = 0
                else if(s.containsAll(seven)) map[six] = 9
                else map[six] = 6
            }
            fives.forEach { five ->
                val f = five.toSet()
                if(f.containsAll(seven)) map[five] = 3
                else if(f.containsAll(four subtract seven)) map[five] = 5
                else map[five] = 2
            }
            total += inOut.last().split(" ").map { v-> map[v.toList().sorted().joinToString("")] }.joinToString(separator = "").toInt()
        }
    }
    println(total)
}