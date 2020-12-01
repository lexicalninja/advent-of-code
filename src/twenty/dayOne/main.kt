package twenty.dayOne

import java.io.File
import java.lang.Integer.parseInt

fun main(args: Array<String>) {
    val start = System.currentTimeMillis()
    // get an array of the numbers from the file
    val report = readFromFile("src/twenty/dayOne/expense.txt")
    findTwo(report)
    findThree(report)
    println("elapsed time: ${System.currentTimeMillis() - start} ms")
}

fun readFromFile(filename: String): MutableList<Int> {
    val array = mutableListOf<Int>()
    File(filename).forEachLine { array.add(parseInt(it)) }
    return array
}

fun findTwo(report: MutableList<Int>) {
    // create hashmap to hold values
    val map = mutableMapOf<String, Int>()
    finder@ for (i in report) {
        map["$i"]?.apply {
            println("find two values: { $this, $i }, answer: ${this * i}")
            return
        }
        map["${2020 - i}"] = i
    }
}

fun findThree(report: MutableList<Int>) {
    // create hashmap to hold values
    val map = hashMapOf<String, Int>()
    for (i in report.indices) {
        for (j in i + 1..report.lastIndex) {
            val k = 2020 - report[i] - report[j]
            if (k > 0) {
                map["$k"]?.apply {
                    println("find three values: [ $this, ${report[i]}, ${report[j]} ] , answer: ${this * report[j] * report[i]}")
                    return
                }
                map["${report[j]}"] = report[j]
            }
        }
    }
}