import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.sync.Mutex

//using conflated channels and locks to process a list in parallel
suspend fun main() = runBlocking {
    var lista = arrayListOf<String>("cuv1", "cuv2", "cuv3", "cuv4", "cuv5", "cuv6", "cuv7")
    var a = Channel<String>(Channel.CONFLATED)
    var mutex= Mutex()

    a.send(lista.get(0))
    val p1 = launch {
            while(lista.isNotEmpty()) {
                try{
                mutex.lock()
                lista.removeAt(0)
                println("proces 1" + "     cu elementul " + a.receive() + this.coroutineContext)
                a.send(lista.removeAt(0))
                mutex.unlock()
                delay(50)
            }catch (_:IndexOutOfBoundsException){}
            }
    }

    val p2 = launch {

        while(lista.isNotEmpty()) {
            mutex.lock()
            println("proces 2" + "     cu elementul " + a.receive() + this.coroutineContext)
            a.send(lista.removeAt(0))
            mutex.unlock()
            delay(75)
        }
    }

    val p3 = launch {
        while(lista.isNotEmpty()) {
            mutex.lock()
            println("proces 3" + "     cu elementul " + a.receive() + this.coroutineContext)
            a.send(lista.get(0))
            mutex.unlock()
            delay(100)
        }
    }

    joinAll()


}

