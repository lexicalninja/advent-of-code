package twentyOne.dayFifteen

import twentyOne.splitIgnoreEmpty
import java.io.File
import java.util.*
import kotlin.collections.HashMap

fun main(args: Array<String>) {
  val map: MutableList<MutableList<Int>> = mutableListOf()
  val edges = mutableListOf<Edge>()
  File("src/twentyOne/dayFifteen/input.txt").useLines { lines ->
    lines.forEach {
      map.add(it.splitIgnoreEmpty("").map { c -> c.toInt() }.toMutableList())
    }
  }
  map.forEachIndexed { i, row ->
    for (j in row.indices) {
      if (i < map.indices.last) edges.add(Edge("${i},$j", "${i + 1},$j", map[i + 1][j]))
      if (j < row.indices.last) edges.add(Edge("${i},$j", "$i,${j + 1}", map[i][j + 1]))
    }
  }
  Graph(edges).apply { runMazeFromTo("0,0", "${map[0].indices.last},${map.indices.last}") }
}

data class Edge(val v1: String, val v2: String, val weight: Int)

data class Vertex(val name: String, var dist: Int = Int.MAX_VALUE, var prev: Vertex? = null, val neighbors: MutableMap<Vertex, Int> = mutableMapOf()) : Comparable<Vertex> {
  fun printPath() {
    when (prev) {
      this -> {
        print(name)
      }
      null -> {
        print("$name(unreached)")
      }
      else -> {
        prev!!.printPath()
        print(" -> $name($dist)")
      }
    }
  }

  override fun compareTo(other: Vertex): Int {
    if (dist == other.dist) return name.compareTo(other.name)
    return dist.compareTo(other.dist)
  }

  override fun toString() = "($name, $dist)"
}

data class Graph(val edges: List<Edge>) {
  private val graph = mutableMapOf<String, Vertex>()

  init {
    for (e in edges) {
      if (!graph.containsKey(e.v1)) graph[e.v1] = Vertex(e.v1)
      if (!graph.containsKey(e.v2)) graph[e.v2] = Vertex(e.v2)
    }

    for (e in edges) {
      println(e)
      graph[e.v1]?.neighbors?.put(graph[e.v2]!!, e.weight)
      // also do this for an undirected graph if applicable
      graph[e.v2]?.neighbors?.put(graph[e.v1]!!, e.weight)
    }
  }

  fun runMazeFromTo(v0: String, vO: String) {
    val source = graph[v0]!!
    val U = TreeSet<Vertex>()

    for (v in graph.values) {
      if (v == source) {
        v.dist = 0
        v.prev = source
      }
      U.add(v)
    }
    runMaze(U, vO)
  }

  private fun runMaze(U: TreeSet<Vertex>, end: String) {
    while (U.isNotEmpty()) {
      val shorty = U.pollFirst()!!
      //unreachable if shorty is MAX
      if (shorty.dist == Int.MAX_VALUE) break
      for (n in shorty.neighbors) {
        val v = n.key

        val newDist = shorty.dist + n.value
        if (newDist < v.dist) {
          U.remove(v)
          v.dist = newDist
          v.prev = shorty
          U.add(v)
        }
        if (v.name == end) {
          println("dist: ${v.dist}")
          v.printPath()
//          return
        }
      }
    }
  }
}
