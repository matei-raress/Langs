import java.io.File

//determine the number of appeareances of each element in a list
fun main() {
    var x = File("intrare.txt").readText().split(",").map { it.trim() }.map { it.toInt() }
    var nr_distinct = x.distinct()
    var aparitii = nr_distinct.map { a -> Pair(a, x.count { it == a }) }

}