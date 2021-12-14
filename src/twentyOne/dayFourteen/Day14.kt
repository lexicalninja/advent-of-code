package twentyOne.dayFourteen

import java.io.File

fun main(args: Array<String>) {
  partOne()
  partTwo()
}

fun partOne() {
  File("src/twentyOne/dayFourteen/input.txt").useLines { lines ->
    val iterator = lines.iterator()
    var line = iterator.next()
    var pairs = line.splitIgnoreEmpty("").zipWithNext().map { "${it.first}${it.second}" }
    val counts: MutableMap<String, Long> = line.splitIgnoreEmpty("").groupingBy { it }.eachCount().toMutableMap().run {
      val result = mutableMapOf<String, Long>()
      this.forEach { (t, u) -> result[t] = u.toLong() }
      result
    }
    val inserts = mutableMapOf<String, Insert>()
    iterator.next()
    while (iterator.hasNext()) {
      line = iterator.next()
      line.split(" -> ").apply {
        val s = this[0].splitIgnoreEmpty("")
        inserts[this[0]] = Insert(mutableListOf("${s[0]}${this[1]}", "${this[1]}${s[1]}"), this[1])
      }
    }

    for (i in 1..10) {
      val temp = mutableListOf<String>()
      pairs.forEach {
        val ins = inserts[it]!!
        temp.addAll(ins.newPairs)
        counts[ins.insertString] = counts.getOrDefault(ins.insertString, 0L) + 1L
      }
      pairs = temp
    }
    println(counts.values.run { maxOf { it } - minOf { it } })
    println(counts)
  }
}

fun partTwo() {
  File("src/twentyOne/dayFourteen/input.txt").useLines { lines ->
    val iterator = lines.iterator()
    var line = iterator.next()
    var pairs = line.splitIgnoreEmpty("").zipWithNext().map { "${it.first}${it.second}" }.groupingBy { it }.eachCount().toMutableMap().run {
      val result = mutableMapOf<String, Long>()
      this.forEach { (t, u) -> result[t] = u.toLong() }
      result
    }
    println(pairs)
    val counts: MutableMap<String, Long> = line.splitIgnoreEmpty("").groupingBy { it }.eachCount().toMutableMap().run {
      val result = mutableMapOf<String, Long>()
      this.forEach { (t, u) -> result[t] = u.toLong() }
      result
    }
    val inserts = mutableMapOf<String, Insert>()
    iterator.next()
    while (iterator.hasNext()) {
      line = iterator.next()
      line.split(" -> ").apply {
        val s = this[0].splitIgnoreEmpty("")
        inserts[this[0]] = Insert(mutableListOf("${s[0]}${this[1]}", "${this[1]}${s[1]}"), this[1])
      }
    }
    for (i in 1..40) {
      val temp = mutableMapOf<String, Long>()
      pairs.forEach { (t, u) ->
        val ins = inserts[t]!!
        temp[ins.newPairs[0]] = temp.getOrDefault(ins.newPairs[0], 0L) + 1L * u
        temp[ins.newPairs[1]] = temp.getOrDefault(ins.newPairs[1], 0L) + 1L * u
        counts[ins.insertString] = counts.getOrDefault(ins.insertString, 0L) + 1L * u
      }
      pairs = temp
      println(counts.values.sumOf { it })
    }
    println(counts.values.run { maxOf { it } - minOf { it } })
  }
}

data class Insert(val newPairs: List<String>, val insertString: String)

fun CharSequence.splitIgnoreEmpty(vararg delimiters: String): List<String> {
  return this.split(*delimiters).filter {
    it.isNotEmpty()
  }
}