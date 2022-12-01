package twentyOne

import java.io.File

fun File.listFromTxt(): List<String> {
    val result: MutableList<String> = mutableListOf()
    useLines { lines ->
        lines.forEach {
            result.add(it)
        }
    }
    return result
}

fun <T> File.listFromTxt(transform: (String) -> T): List<T> {
    val result: MutableList<T> = mutableListOf()
    useLines { lines ->
        lines.forEach {
            val value = transform.run { this(it) }
            result.add(value)
        }
    }
    return result
}

fun CharSequence.splitIgnoreEmpty(vararg delimiters: String): List<String> {
    return this.split(*delimiters).filter {
        it.isNotEmpty()
    }
}