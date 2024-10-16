package org.example

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
//procesare pipeline a unui ADT, primul thread inmulteste elem vectorului cu o constanta, urmatorul thread ordoneaza elem, ultimul thread va afisa

class Multiply(var adt: ArrayList<Int>, var alpha: Int) : Runnable {
    override fun run() {
        for (i in adt.indices){
            adt[i] = adt[i] * alpha
        }
    }
}

class Sort(var adt: ArrayList<Int>) : Runnable {
    override fun run() {
        adt.sort()
    }
}

class Print(var adt: ArrayList<Int>) : Runnable {

    override fun run() {
        println("Thread list is: " + adt)
    }
}

fun main() {

    var ADT = arrayListOf(6, 3, 10, 6, 1, 2)
    var alpha=5
    val multiply = Thread(Multiply(ADT, alpha))
    val sort = Thread(Sort(ADT))
    val print = Thread(Print(ADT))

    println("Initial list is: " + ADT)
    multiply.run()
    sort.run()
    print.run()


    ADT = arrayListOf(6, 3, 10, 6, 1, 2)

    runBlocking{
        launch{
            for(i in ADT.indices){
                ADT[i]*=alpha
            }
        }

        launch{
            ADT.sort()
        }
        launch{
            println("Coroutine list is: " + ADT)
        }
    }
}

