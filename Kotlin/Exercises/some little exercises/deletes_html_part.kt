package org.example

import java.io.File

//deletes different parts of an html  using an aplicativ
fun String.removeTag(tag: String): String {
    var result = this
    do {
        val begin = result.indexOf("<$tag")
        val end = result.indexOf("</$tag>")
        result = result.substring(0, begin) + result.substring(end + "</$tag>".length, result.length)
    } while (result.contains(Regex("<$tag")))
    return result
}

fun <T, R> List<T>.applicative(func: List<(T) -> R>): List<R> = func.flatMap { f -> this.map(f) }

fun main() {

    val file = File("html")
    var s = file.readLines().joinToString("\n")
    //println(s.removeTag("script"))
    var function = listOf<(String) -> String>({ str -> str.removeTag("script") }, { str -> str.removeTag("style") })

    val result = listOf(s).applicative(function)
    println(result)

}

