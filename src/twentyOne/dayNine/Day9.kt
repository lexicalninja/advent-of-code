package twentyOne.dayNine

import java.io.File

fun main(args: Array<String>) {
    partOne()
    partTwo()
}

fun partOne() {
    val map = mutableListOf<MutableList<Int>>()
    val lowSpots = mutableListOf<Int>()
    File("src/twentyOne/dayNine/input.txt").useLines { lines ->
        lines.forEach {
            map.add(it.map { s -> Character.getNumericValue(s) }.toMutableList())
        }
    }
    map.forEachIndexed { i, list ->
        for(j in list.indices) {
            map.checkIsLowSpot(i, j)?.apply { lowSpots.add(this) }
        }
    }
    println(map)
    println(lowSpots)
    println(lowSpots.sum() + lowSpots.size)
}

fun partTwo() {
    val map = mutableListOf<MutableList<Int>>()
    File("src/twentyOne/dayNine/input.txt").useLines { lines ->
        lines.forEach {
            map.add(it.map { s -> Character.getNumericValue(s) }.toMutableList())
        }
    }
    val sinks = mutableListOf<Pair<Int, Int>>()
    map.forEachIndexed { i, list ->
        for(j in list.indices) {
            map.checkIsSinkCell(i, j)?.apply { sinks.add(this)}
        }
    }
    val sizes = mutableListOf<Int>()
    for (sink in sinks) {
        sizes.add(getBasinSizeForSink(map, sink))
    }
    println(sizes.sortedDescending().take(3).reduce { acc, i -> acc * i })
}

fun MutableList<MutableList<Int>>.checkIsLowSpot(row: Int, col: Int): Int? {
    val center = this[row][col]
    getOrNull(row - 1)?.getOrNull(col)?.apply { if (center >= this) return null }

    getOrNull(row)?.getOrNull(col - 1)?.apply { if (center >= this) return null }
    getOrNull(row)?.getOrNull(col + 1)?.apply { if (center >= this) return null }

    getOrNull(row + 1)?.getOrNull(col)?.apply { if (center >= this) return null }
    return center
}

fun MutableList<MutableList<Int>>.checkIsSinkCell(row: Int, col: Int): Pair<Int, Int>? {
    val center = this[row][col]
    getOrNull(row - 1)?.getOrNull(col)?.apply { if (center >= this) return null }

    getOrNull(row)?.getOrNull(col - 1)?.apply { if (center >= this) return null }
    getOrNull(row)?.getOrNull(col + 1)?.apply { if (center >= this) return null }

    getOrNull(row + 1)?.getOrNull(col)?.apply { if (center >= this) return null }
    return Pair(row, col)
}

fun getBasinSizeForSink(arr: MutableList<MutableList<Int>>, sink: Pair<Int, Int>): Int{
    val counted = MutableList(arr.size){MutableList(arr.first().size){false} }
    return getHigherNeighbors(arr, sink.first, sink.second, counted)
}

fun getHigherNeighbors(arr: MutableList<MutableList<Int>>, row: Int, col: Int, counted: MutableList<MutableList<Boolean>>): Int {
    counted[row][col] = true
    var result = 1
    val center = arr[row][col]
    arr.getOrNull(row - 1)?.getOrNull(col)?.apply { if (this != 9 && center < this && !counted[row-1][col]) result += getHigherNeighbors(arr, row -1, col, counted) }

    arr.getOrNull(row)?.getOrNull(col - 1)?.apply { if (this != 9 && center < this && !counted[row][col-1]) result += getHigherNeighbors(arr, row, col -1, counted) }
    arr.getOrNull(row)?.getOrNull(col + 1)?.apply { if (this != 9 && center < this && !counted[row][col+1]) result += getHigherNeighbors(arr, row, col +1, counted) }

    arr.getOrNull(row + 1)?.getOrNull(col)?.apply { if (this != 9 && center < this && !counted[row+1][col]) result += getHigherNeighbors(arr, row +1, col, counted) }
    return result
}



