package twentyOne.daySix

import java.io.File

fun main(args: Array<String>) {
    val fishVector = MutableList(9) { 0L }
    val transform = mutableListOf(
        mutableListOf(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 1L),
        mutableListOf(1L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L),
        mutableListOf(0L, 1L, 0L, 0L, 0L, 0L, 0L, 0L, 1L),
        mutableListOf(0L, 0L, 1L, 0L, 0L, 0L, 0L, 0L, 0L),
        mutableListOf(0L, 0L, 0L, 1L, 0L, 0L, 0L, 0L, 0L),
        mutableListOf(0L, 0L, 0L, 0L, 1L, 0L, 0L, 0L, 0L),
        mutableListOf(0L, 0L, 0L, 0L, 0L, 1L, 0L, 0L, 0L),
        mutableListOf(0L, 0L, 0L, 0L, 0L, 0L, 1L, 0L, 0L),
        mutableListOf(0L, 0L, 0L, 0L, 0L, 0L, 0L, 1L, 0L),
    )
    File("src/twentyOne/daySix/input.txt").useLines { lines ->
        lines.forEach { line ->
            line.split(",").forEach { i -> fishVector[i.toInt()] += 1L }
        }
    }
    fishVector.reverse()
    println(transform(transform, fishVector, 256).sum())

}

fun dot(A: MutableList<MutableList<Long>>, x: MutableList<Long>): MutableList<Long> {
    val result = MutableList(x.size) { 0L }
    for (i in A.indices) {
        for (j in A[i].indices) {
            result[i] += A[i][j] * x[j]
        }
    }
    return result
}

fun transform(A: MutableList<MutableList<Long>>, x: MutableList<Long>, steps: Int): MutableList<Long> {
    return if (steps == 0) x
    else transform(A, dot(A, x), steps - 1)
}