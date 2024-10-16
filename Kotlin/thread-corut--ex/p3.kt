package com.pp.laborator

import kotlinx.coroutines.*
import java.lang.Runnable
import java.util.*
import java.util.concurrent.locks.ReentrantLock
// calcul simultan pt 4 valori diferite ale lui n luate dintr-o coada de catre 4 corutine diferite

class SimpleThread(var nums:Queue<Int>): Runnable{

    override fun run() {
        var queueIterator = nums.iterator()
        while (queueIterator.hasNext()) {
            var number = queueIterator.next()

            var sum = number * (number + 1) / 2
            println("Numar: [$number] -> Suma: [$sum]")
        }

    }
}

fun main(){
    var nums : Queue<Int> = LinkedList<Int>()

    nums.add(7)
    nums.add(10)
    nums.add(5)
    nums.add(20)

    var thread1 = SimpleThread(nums)

    thread1.run()
    
    runBlocking {
        launch {
            var queueIterator = nums.iterator()
            while (queueIterator.hasNext()) {
                var number = queueIterator.next()

                var sum = number * (number + 1) / 2
                println("Numar: [$number] -> Suma: [$sum]")
            }
        }
        delay(1000)
    }

}