package twentyOne.dayTen

import java.io.File

val openingSymbols = listOf('(', '[', '{', '<')
val corruptValues = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
val autoCompleteValues = mapOf('(' to 1, '[' to 2, '{' to 3, '<' to 4)
val symbolPairs = mapOf(')' to '(', ']' to '[', '}' to '{', '>' to '<')

fun main(args: Array<String>) {
    File("src/twentyOne/dayTen/input.txt").useLines { lines ->
        var coruptionTotal = 0
        val autoCompleteTotals = mutableListOf<Long>()
        lines.forEach line@{ line ->
            val stack: Stack<Char> = mutableListOf()
            line.forEach char@{ c ->
                if (c in openingSymbols) stack.push(c)
                else {
                    when {
                        stack.peek() == symbolPairs[c] -> stack.pop()
                        else -> {
                            coruptionTotal += corruptValues[c]!!
                            return@line
                        }
                    }
                }
            }
            if (stack.peek() != null) {
                var total = 0L
                while (stack.peek() != null) {
                    total = total * 5 + autoCompleteValues[stack.pop()]!!
                }
                autoCompleteTotals.add(total)
            }
        }
        autoCompleteTotals.sort()
        println(coruptionTotal)
        println(autoCompleteTotals[autoCompleteTotals.size / 2])
    }
}

// this is a stack in Kotlin
typealias Stack<T> = MutableList<T>

fun <T> Stack<T>.push(item: T) = add(item)
fun <T> Stack<T>.pop(): T? = if (isNotEmpty()) removeAt(lastIndex) else null
fun <T> Stack<T>.peek(): T? = if (isNotEmpty()) this[lastIndex] else null