package twentyOne.dayTwelve

import java.io.File

fun main(args: Array<String>) {

  val nodes = mutableMapOf<String, Node>()
  File("src/twentyOne/dayTwelve/input.txt").useLines { lines ->
    lines.forEach {
      it.split("-").run {
        nodes.getOrPut(this[0]) { Node(this[0]) }.adjacent.add(nodes.getOrPut(this[1]) { Node(this[1]) })
        nodes[this[1]]!!.adjacent.add(nodes[this[0]]!!)
      }
    }
  }
  println(getPath(mutableListOf(), nodes["start"]!!).size)
  println(getPathAlt(mutableListOf(), nodes["start"]!!).size)
}

fun getPath(path: MutableList<String>, node: Node): MutableList<MutableList<String>> {
  val result = mutableListOf<MutableList<String>>()
  val newPath = ArrayList(path)
  if (node.isEnd) {
    newPath.add(node.id)
    result.add(newPath)
    return result
  } else {
    for (a in node.adjacent) {
      if (a.canRevisit) {
        newPath.add(node.id)
        result.addAll(getPath(newPath, a))
      } else if (!a.isStart && !path.contains(a.id)) {
        newPath.add(node.id)
        result.addAll(getPath(newPath, a))
      }
    }
  }
  return result
}

fun getPathAlt(path: MutableList<String>, node: Node): MutableList<MutableList<String>> {
  return if (node.isEnd) {
    val newPath = ArrayList(path)
    newPath.add(node.id)
    (mutableListOf(newPath))
  } else {
    val result = mutableListOf<MutableList<String>>()
    for (a in node.adjacent) {
      if (!a.isStart && !(node.isLower && path.contains(node.id) && path.filter { it.matches("[a-z]+".toRegex()) }.hasDuplicates())) {
        val newPath = ArrayList(path)
        newPath.add(node.id)
        result.addAll(getPathAlt(newPath, a))
      }
    }
    result
  }
}

data class Node(val id: String, val adjacent: MutableList<Node> = mutableListOf()) {
  val isStart = id == "start"
  val isEnd = id == "end"
  val canRevisit = id.matches("[A-Z]+".toRegex()) && id != "start" && id != "end"
  val isLower: Boolean

  init {
    isLower = id.matches("[a-z]+".toRegex())
  }
}

fun List<String>.hasDuplicates(): Boolean {
  val freq = this.groupingBy { it }.eachCount()
  freq.values.forEach {
    if (it > 1) {
      return true
    }
  }
  return false
}
