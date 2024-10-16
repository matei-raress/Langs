//using generics and extensions to find if all the numbers from a list are primes
fun List<Number>.allPrimes(): Boolean {
    return !this.any { it !is Int || !it.isPrime() }
}

fun Int.isPrime(): Boolean {
    if (this <= 1)
        return false
    if (this in 2..3)
        return true
    var a = 2.until(this / 2 + 1)
    return !a.any { this % it == 0 }

}

fun List<Number>.Primes(): Boolean {
    var a = this.filter { it is Int } as List<Int> //raman inturi
    if (a.isEmpty()) return false
    println(a)
    return !a.any { !it.isPrime() }

}

fun main() {
    val lists = listOf(
        listOf(1, 2, 3, 4, 5),
        listOf(1.0, 2.0, 3.0),
        listOf(5, 3, 7, 11)
    )
    lists.forEachIndexed { index, list ->
        println(" ${list.Primes()}")
    }

    println()
    lists.forEachIndexed { index, list ->
        println("List $index: $list -> all are primes: ${list.allPrimes()}")
    }
}