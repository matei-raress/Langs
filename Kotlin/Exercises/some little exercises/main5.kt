package com.pp.laborator

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
//from a file, filter the words with a length<4 and get only the middle of all the words
fun main() = runBlocking<Unit>{
    launch{
        var str=String()
        var line = File("intrare.txt").readText().split(",").filter{it.length>3}.forEach(){
            var a:Int= it.length/2

            str+=it.get(a-1)
            str+=it.get(a)

        }
        println(line)
        println(str)


    }
}