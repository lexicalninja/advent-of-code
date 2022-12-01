import twentyOne.splitIgnoreEmpty
import java.io.File
import java.util.TreeSet

class Edge(val v1: String, val v2: String, val dist: Int)

/** One vertex of the graph, complete with mappings to neighbouring vertices */
class Vertex(val name: String) : Comparable<Vertex> {

  var dist = Int.MAX_VALUE  // MAX_VALUE assumed to be infinity
  var previous: Vertex? = null
  val neighbours = HashMap<Vertex, Int>()

  fun printPath() {
    if (this == previous) {
      print(name)
    } else if (previous == null) {
      print("$name(unreached)")
    } else {
      previous!!.printPath()
      print(" -> $name($dist)")
    }
  }

  override fun compareTo(other: Vertex): Int {
    if (dist == other.dist) return name.compareTo(other.name)
    return dist.compareTo(other.dist)
  }

  override fun toString() = "($name, $dist)"
}

class Graph(
  val edges: List<Edge>,
  val directed: Boolean,
  val showAllPaths: Boolean = false
) {
  // mapping of vertex names to Vertex objects, built from a set of Edges
  private val graph = HashMap<String, Vertex>(edges.size)

  init {
    // one pass to find all vertices
    for (e in edges) {
      if (!graph.containsKey(e.v1)) graph.put(e.v1, Vertex(e.v1))
      if (!graph.containsKey(e.v2)) graph.put(e.v2, Vertex(e.v2))
    }

    // another pass to set neighbouring vertices
    for (e in edges) {
      graph[e.v1]!!.neighbours.put(graph[e.v2]!!, e.dist)
      // also do this for an undirected graph if applicable
      if (!directed) graph[e.v2]!!.neighbours.put(graph[e.v1]!!, e.dist)
    }
  }

  /** Runs dijkstra using a specified source vertex */
  fun dijkstra(startName: String) {
    if (!graph.containsKey(startName)) {
      println("Graph doesn't contain start vertex '$startName'")
      return
    }
    val source = graph[startName]
    val q = TreeSet<Vertex>()

    // set-up vertices
    for (v in graph.values) {
      v.previous = if (v == source) source else null
      v.dist = if (v == source) 0 else Int.MAX_VALUE
      q.add(v)
    }

    dijkstra(q)
  }

  /** Implementation of dijkstra's algorithm using a binary heap */
  private fun dijkstra(q: TreeSet<Vertex>) {
    while (!q.isEmpty()) {
      // vertex with shortest distance (first iteration will return source)
      val u = q.pollFirst()
      // if distance is infinite we can ignore 'u' (and any other remaining vertices)
      // since they are unreachable
      if (u.dist == Int.MAX_VALUE) break

      //look at distances to each neighbour
      for (a in u.neighbours) {
        val v = a.key // the neighbour in this iteration

        val alternateDist = u.dist + a.value
        if (alternateDist < v.dist) { // shorter path to neighbour found
          q.remove(v)
          v.dist = alternateDist
          v.previous = u
          q.add(v)
        }
      }
    }
  }

  /** Prints a path from the source to the specified vertex */
  fun printPath(endName: String) {
    if (!graph.containsKey(endName)) {
      println("Graph doesn't contain end vertex '$endName'")
      return
    }
    print(if (directed) "Directed   : " else "Undirected : ")
    graph[endName]!!.printPath()
    println()
    if (showAllPaths) printAllPaths() else println()
  }

  /** Prints the path from the source to every vertex (output order is not guaranteed) */
  private fun printAllPaths() {
    for (v in graph.values) {
      v.printPath()
      println()
    }
    println()
  }
}


fun main(args: Array<String>) {
  var map: MutableList<MutableList<Int>> = mutableListOf()
  val edges = mutableListOf<Edge>()
  File("src/twentyOne/dayFifteen/input.txt").useLines { lines ->
    lines.forEach {
      map.add(it.splitIgnoreEmpty("").map { c -> c.toInt() }.toMutableList())
    }
  }
  var temp = map.toMutableList()

  map.forEachIndexed { i, l ->
    var t = l.toMutableList()
    for (x in 1..4) {
      val j = t.map { if (it != 9) it + 1 else 1 }
      temp[i].addAll(j)
      t = j.toMutableList()
    }
  }
  map = temp
  temp = map.toMutableList()
  val temp2 = mutableListOf<MutableList<Int>>()
  for (x in 1..4) {
    for (t in temp) {
      temp2.add(t.map { if (it != 9) it + 1 else 1 }.toMutableList())
    }
    map.addAll(temp2)
    temp = temp2.toMutableList()
    temp2.clear()
  }

  map.forEach { println(it.joinToString("")) }
  map[0][0] = 0
  map.forEachIndexed { i, row ->
    for (j in row.indices) {
      if (i < map.indices.last) edges.add(Edge("${i},$j", "${i + 1},$j", map[i + 1][j]))
      if (j < row.indices.last) edges.add(Edge("${i},$j", "$i,${j + 1}", map[i][j + 1]))
    }
  }
  val START = "0,0"
  val END = "${map[0].indices.last},${map.indices.last}"
  with (Graph(edges, true)) {   // directed
    dijkstra(START)
    printPath(END)
  }
  with (Graph(edges, false)) {  // undirected
    dijkstra(START)
    printPath(END)
  }
}