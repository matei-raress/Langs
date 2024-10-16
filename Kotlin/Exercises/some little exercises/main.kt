import javafx.application.Application.launch
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED

//using conflated channels to process a list in parallel
suspend fun main() = coroutineScope {
    var lista = arrayListOf<String>("cuv1", "cuv2", "cuv3", "cuv4", "cuv5", "cuv6", "cuv7")
    var a = Channel<String>(CONFLATED)

    a.send(lista.get(0))
    while (lista.isNotEmpty()) {
        lista.removeAt(0)

            val p1 = launch {
                try {
                    println("proces 1" + "     cu elementul " + a.receive() + this.coroutineContext)
                    a.send(lista.removeAt(0))
                }
                catch (_:java.lang.IndexOutOfBoundsException){}
                finally {
                }
            }
            delay(100)
            val p2 = launch {
                println("proces 2" + "     cu elementul " + a.receive() + this.coroutineContext)
                a.send(lista.removeAt(0))
            }
            delay(100)
            val p3 = launch {
                println("proces 3" + "     cu elementul " + a.receive() + this.coroutineContext)
                a.send(lista.get(0))
            }

        delay(100)
    }

    joinAll()

}