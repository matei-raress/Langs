import kotlinx.coroutines.runBlocking

//using mediator design pattern to synchronize some ants
class Ant(val mediator: Mediator, var nume: String) {

    fun send(mesaj: String) {
        println("Furnica " + nume + " a trimis " + mesaj)
        mediator.notifyFurnici(this, mesaj)
    }
    fun recieve(mesaj: String) {
        println("Furnica " + nume + " a primit mesajul " + mesaj)
    }
}

class Mediator {
    var listaFurnici = mutableListOf<Ant>()
    fun add(ant: Ant) {
        listaFurnici.add(ant)
    }

    fun notifyFurnici(antSend: Ant, mesaj: String) {
        for (furnica in listaFurnici) {
            if (furnica != antSend) {
                furnica.recieve(mesaj)
            }
        }
    }
}

fun main() = runBlocking {
    var mediator = Mediator()

    var f1 = Ant(mediator, "Andrei")
    var f2 = Ant(mediator, "Cartof")
    var f3 = Ant(mediator, "Plamta")

    mediator.add(f1)
    mediator.add(f2)
    mediator.add(f3)
    f1.send("hello ")

}