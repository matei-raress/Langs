import kotlinx.coroutines.*
import java.io.File
import java.lang.Runnable
import java.util.*
import java.util.concurrent.locks.ReentrantLock

class SimpleThread(var nums:Queue<File>): Runnable{

    override fun run() {
        var queueIterator = nums.iterator()
        while (queueIterator.hasNext()) {
            var number = queueIterator.next()
            number.writeText("Stamp")
        }

    }
}

fun main(){
    var files : Queue<File> = LinkedList()
    var file=File("file1.txt")

    files.add(file)
    file=File("file2.txt")
    files.add(file)
    file=File("file3.txt")
    files.add(file)
    file=File("file4.txt")
    files.add(file)

    var thread1 = SimpleThread(files)

    thread1.run()

    runBlocking {
        launch {
            var queueIterator = files.iterator()
            while (queueIterator.hasNext()) {
                var number = queueIterator.next()

                number.writeText("Stampila")
            }
        }
        delay(1000)
    }

}