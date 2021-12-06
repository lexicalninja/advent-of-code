package twentyOne.dayFour

import java.io.File

fun main(args: Array<String>) {
    partTwo()
//    println(balls)
//    println(cards.last())last

}

fun partOne() {
    val balls = mutableListOf<Int>()
    val cards = mutableListOf<BingoCard>()
    var inputRow = 0
    File("src/twentyOne/dayFour/input.txt").useLines { lines ->
        lines.forEach {
            if (inputRow == 0) {
                it.split(",").forEach { s -> balls.add(s.toInt()) }
            } else {
                if (it.isNotEmpty()) {
                    if (cards.isEmpty() || cards.last().isFull()) cards.add(BingoCard())
                    cards.last().addRow(it.trim().split( " +".toRegex()).map { s -> s.toInt() }.toMutableList())
                    if (cards.last().isFull()) cards.last().transposeRows()
                }
            }
            inputRow++
        }
    }
    while (balls.isNotEmpty()) {
        val ball = balls.removeAt(0)
        cards.forEach{
            it.markCard(ball)
            if(it.hasBingo()) {
                println(it.sumUncovered() * ball)
                balls.clear()
            }
        }
    }
}

fun partTwo() {
    val balls = mutableListOf<Int>()
    val cards = mutableListOf<BingoCard>()
    var inputRow = 0
    File("src/twentyOne/dayFour/input.txt").useLines { lines ->
        lines.forEach {
            if (inputRow == 0) {
                it.split(",").forEach { s -> balls.add(s.toInt()) }
            } else {
                if (it.isNotEmpty()) {
                    if (cards.isEmpty() || cards.last().isFull()) cards.add(BingoCard())
                    cards.last().addRow(it.trim().split( " +".toRegex()).map { s -> s.toInt() }.toMutableList())
                    if (cards.last().isFull()) cards.last().transposeRows()
                }
            }
            inputRow++
        }
    }
    while (balls.isNotEmpty()) {
        val ball = balls.removeAt(0)
        val iterator = cards.iterator()
        while (iterator.hasNext()) {
            val card = iterator.next()
            card.markCard(ball)
            if(card.hasBingo()) {
                if(cards.size  != 1){
                    iterator.remove()
                } else {
                    println(card.sumUncovered() * ball)
                    return
                }
            }
        }
    }
}

data class BingoCard(
    val rows: MutableList<MutableList<Int>> = mutableListOf(),
    val cols: MutableList<MutableList<Int>> = MutableList(5) { MutableList(5) { 0 } }
) {

    fun addRow(row: MutableList<Int>) {
        if (!isFull()) {
            rows.add(row)
        }
    }

    fun isFull(): Boolean = this.rows.size >= 5

    fun transposeRows() {
        rows.forEachIndexed { i, ints ->
            ints.forEachIndexed { j, int ->
                cols[j][i] = int
            }
        }
    }

    fun markCard(ball: Int) {
        rows.forEachIndexed { i, ints ->
            ints.forEachIndexed { j, _ ->
                if (rows[i][j] == ball){
                    rows[i][j] = -1
                    cols[j][i] = -1
                    return
                }

            }
        }
    }

    fun hasBingo(): Boolean {
        for (row in rows) {
            if (row.sum() == -5) return true
        }
        for (col in cols) {
            if (col.sum() == -5) return true
        }
        return false
    }

    fun sumUncovered(): Int {
        return rows.map { it.filter { it > 0 }.sum() }.sum()
    }

    override fun toString(): String {
        return rows.toString()
    }

}
