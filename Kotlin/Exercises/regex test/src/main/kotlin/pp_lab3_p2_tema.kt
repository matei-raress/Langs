import java.io.*
import java.util.*

fun main() {

    var Lista: String = File("LabPP3.txt").readText(Charsets.UTF_8)

    // spatiile multiple
    var regexvar: Regex = Regex(" {2,}")
    Lista = Lista.replace(regexvar, " ")

    //nr paginilor
    regexvar = Regex(" {2,}[0-9]+ {2,}]")
    Lista = Lista.replace(regexvar, "")

    // linii noi multiple
    regexvar = Regex("\n{2,}")
    Lista = Lista.replace(regexvar, "")

    // val ebook=PrintWriter("out1.txt").write(Lista)

    File("out.txt").writeText(Lista)
}