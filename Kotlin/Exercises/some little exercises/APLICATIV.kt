import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.io.File
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue

//examples of an aplictiv
fun <T,R>List<T>.ap(fab:List<(T)->R>):List<R> = fab.flatMap { this.map(it) }

fun <V,R,K>Map<K,V>.aplic(funct:Map<(K,V)->R,V>): Map<R, V> = funct.map{Pair(it.key,it.value)} as Map<R, V>


fun a(B:String):String= B+2


fun main()= runBlocking {


    val numbers= File("text.txt").readText().split(' ').map{ it.toInt() }
    val functii=listOf<(Int)->Int>({i->i/3},{i->i*5})
    val task=async{ numbers.ap(functii) }
    val newNumbers=task.await()
    println(newNumbers)
}