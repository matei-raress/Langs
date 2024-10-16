import java.io.*
import java.util.*

fun main(args: Array<String>) {

    var Dictionar = hashMapOf<String, String>(
        "Once" to "Odata",
        "upon" to "ca",
        "a" to "",
        "time" to "niciodata",
        "there" to "acolo",
        "was" to "a fost",
        "an" to "o",
        "old" to "batrana",
        "woman" to "femeie",
        "who" to "care",
        "loved" to "iubea",
        "baking" to "sa gateasca",
        "gingerbread" to "turta dulce",
        "She" to "Ea",
        "would" to "ar fi",
        "bake" to "gatit",
        "gingerbread" to "turta dulce",
        "cookies" to "biscuiti",
        "cakes" to "prajituri",
        "houses" to "case",
        "and" to "si",
        "people" to "oameni",
        "all" to "toti",
        "decorated" to "decorati",
        "with" to "cu",
        "chocolate" to "ciocolata",
        "peppermint" to "menta",
        "caramel" to "caramel",
        "candies" to "bomboane",
        "colored" to "colorate",
        "ingredients" to "ingrediente"
    )
    val input = Scanner(System.`in`)

    print("Cuvantul in engleza: ")
    var eng = input.nextLine()
    print("Cuvantul in romana: ")
    var rom: String = input.nextLine()
    Dictionar[eng] = rom

    //citire din fisier
    val text: List<String> = File("citire.txt").readLines()
    var Cuvinte: List<String>

    text.forEach() { linie ->
        Cuvinte = linie.split(" ")
        val eng: String = Cuvinte.elementAt(0)
        Dictionar.put(Cuvinte[0], Cuvinte[1])
    }

    print(Dictionar)

    val Poveste =
        "Once upon a time there was an easy old woman who loved baking gingerbread. She would bake gingerbread cookies, cakes, houses and gingerbread people, all decorated with chocolate and peppermint, caramel candies and colored ingredients."
    val words1 = Poveste.split(" ")
    println("Cuvintele din poveste [${words1.count()}]:")
    for (word in words1) {
        print(word + " ")
    }

    val words2 = mutableListOf<String>()
    for (word in words1) {
        words2.add(word.trim(',', '.'))
    }
    println("\n")

    println("Povestea tradusa ar suna cam asa:")
    for (item in words2) {
        if (Dictionar.contains(item))
            print(Dictionar[item])
        else
            print("[$item]")
        print(" ")
    }

    var file = PrintWriter("out.text")
    for (value in words2) {
        var tradus: String? = Dictionar[value]
        file.append(tradus + " ")
    }
    file.close()
}