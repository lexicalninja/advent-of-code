package twentyOne.dayThirteen

import java.io.File

fun main(args: Array<String>) {

  File("src/twentyOne/dayThirteen/input.txt").useLines { lines ->
    lateinit var instructions: MutableList<MutableList<Int>>
    var rows = 0
    var cols = 0
    val xVals = mutableListOf<Int>()
    val yVals = mutableListOf<Int>()
    val folds = mutableListOf<String>()

    val iterator = lines.iterator()
    var line = iterator.next()
    while (line != "") {
      line.split(",").apply {
        val x = first().toInt()
        val y = last().toInt()
        if (x > cols) cols = x
        if (y > rows) rows = y
        xVals.add(x)
        yVals.add(y)
      }
      line = iterator.next()
    }
    instructions = MutableList(rows + 1) { MutableList(cols + 1) { 0 } }
    for (i in yVals.indices) instructions[yVals[i]][xVals[i]] = 1

    while (iterator.hasNext()) {
      line = iterator.next()
      folds.add(line)
    }

    folds[0].substringAfter("fold along ").split("=").apply {
      val axis = first()
      val coord = last().toInt()
      val partOne = if (axis == "y") flipVertical(instructions, coord)
      else flipHorizontal(instructions, coord)
      println("Part One answer: ${partOne.sumOf { it.sum() }}\n")
    }


    for (fold in folds) {
      fold.substringAfter("fold along ").split("=").apply {
        val axis = first()
        val coord = last().toInt()
        instructions = if (axis == "y") flipVertical(instructions, coord)
        else flipHorizontal(instructions, coord)
      }
    }
    println("PART TWO ANSWER")
    printPretty(instructions)
  }

}

fun flipVertical(arr: MutableList<MutableList<Int>>, foldRow: Int): MutableList<MutableList<Int>> {
  val result = MutableList(foldRow) { mutableListOf<Int>() }
  val top = arr.take(foldRow)
  val bottom = arr.takeLast(foldRow).reversed()
  for (i in top.indices) {
    result[i] = top[i].zip(bottom[i]) { a, b -> if (a == 1 || b == 1) 1 else 0 }.toMutableList()
  }
  return result
}

fun flipHorizontal(arr: MutableList<MutableList<Int>>, foldCol: Int): MutableList<MutableList<Int>> {
  val result = MutableList(arr.size) { mutableListOf<Int>() }
  for (i in arr.indices) {
    result[i] = arr[i].take(foldCol).zip(arr[i].takeLast(foldCol).reversed()) { a, b -> if (a == 1 || b == 1) 1 else 0 }.toMutableList()
  }
  return result
}

fun printPretty(arr: MutableList<MutableList<Int>>) {
  for (i in arr.indices) {
    println(arr[i].joinToString("").replace("0", " ").replace("1", "X"))
  }
}
