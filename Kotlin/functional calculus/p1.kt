fun main() {
    var list = listOf(1, 21, 75, 39, 7, 2, 35, 3, 31, 7, 8)
    //a delete numbers <5
    val greater5 = list.filter { it >= 5 }.toMutableList()
    println(greater5)

    //b group numbers into pairs
    var pairs = greater5.zipWithNext().toMutableList()

    val correctPair = pairs.filterIndexed { index, s -> index % 2 == 0 }
    println(correctPair)

/*
    for (i in 1..(pairs.size / 2)) {
        pairs.removeAt(i)
    }
*/

    //c multiply the pairs
    var pairsMul = correctPair.map { it.first * it.second }

    println(pairsMul)

    //d sum the remaining numbers
    println(pairsMul.reduce { x, y -> x + y })

}




