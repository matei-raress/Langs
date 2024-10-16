import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.BufferedWriter
import java.io.File
import java.io.PrintWriter
import java.util.EnumSet.range
import java.util.concurrent.locks.ReentrantLock
//using adapter design pattern to write different types of file in a single file
class Adapter(var lista: MutableList<File>) {

    fun translate(): MutableList<String> {
        return lista.map { it.readText() }.toMutableList()
    }

}

interface Writer {
    fun write()
}

class WriteFile(var lista: MutableList<String>):Writer {
    override fun write() = runBlocking() {
        lista.forEach { launch { File("iesire.txt").appendText(it) } }

    }
}


fun main() {
    var intrare = mutableListOf<File>(File("intrare1.txt"), File("intrare2.io"), File("intrare3.lp"))
    // var iesire=File("iesire.txt").appendText(intrare[0].readText())
    var adapter=Adapter(intrare)
    var adaptedFile=adapter.translate()
    var a:Writer=WriteFile(adaptedFile)
    a.write()

}