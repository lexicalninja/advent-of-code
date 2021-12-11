package twentyOne.dayEleven

import java.io.File

fun main(args: Array<String>) {
  partOne()
  partTwo()
}

fun partOne() {
  val graph = Graph()
  File("src/twentyOne/dayEleven/input.txt").useLines { lines ->
    lines.forEachIndexed { i, s ->
      s.forEachIndexed { j, c ->
        graph.addVertex(Vertex(graph, id = i * s.length + j, weight = Character.getNumericValue(c)))
      }
    }
  }
  for (i in 1..100) {
    graph.update()
  }
  println(graph.getFlashes())
}

fun partTwo() {
  val graph = Graph()
  File("src/twentyOne/dayEleven/input.txt").useLines { lines ->
    lines.forEachIndexed { i, s ->
      s.forEachIndexed { j, c ->
        graph.addVertex(Vertex(graph, id = i * s.length + j, weight = Character.getNumericValue(c)))
      }
    }
  }

  for (i in 1..Int.MAX_VALUE) {
    graph.update()
    if (graph.synchronized()) {
      println("synced at step $i")
      break
    }
  }
}

data class Graph(val vertices: MutableList<Vertex> = mutableListOf()) {
  fun addVertex(v: Vertex) = vertices.add(v)
  fun update() {
    for (v in vertices) {
      v.resetAndIncrease()
    }
    for (v in vertices) {
      v.checkFlash()
    }
  }

  fun synchronized() = vertices.all { it.flashed }

  fun getFlashes() = vertices.sumOf { it.flashes }

  fun toGrid() {
    for (i in 0..9) {
      println(vertices.subList(i * 10, i * 10 + 10).map { it.weight }.joinToString(""))
    }
  }
}

data class Vertex(val graph: Graph, val id: Int, var weight: Int = 0, var flashes: Int = 0, var flashed: Boolean = false) {
  private val adjacent = mutableListOf(-11, -10, -9, -1, 1, 9, 10, 11)
  private val rightEdgeAdjacent = mutableListOf(-11, -10, -1, 9, 10)
  private val leftEdgeAdjacent = mutableListOf(-10, -9, 1, 10, 11)
  private val adjacents: MutableList<Int>
    get() {
      return when (id % 10) {
        0 -> leftEdgeAdjacent
        9 -> rightEdgeAdjacent
        else -> adjacent
      }
    }

  private fun increase() {
    weight++
  }

  fun resetAndIncrease() {
    flashed = false
    increase()
  }

  fun checkFlash() {
    if (weight > 9 && !flashed) {
      flashed = true
      flashes++
      for (adj in adjacents) {
        graph.vertices.getOrNull(id + adj)?.also {
          if (!it.flashed) {
            it.increase()
            it.checkFlash()
          }
        }
      }
      weight = 0
    }
  }
}
