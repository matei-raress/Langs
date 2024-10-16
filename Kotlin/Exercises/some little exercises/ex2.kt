import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File
import java.util.Random
//find pairs with a certain conditions in 2 files
fun main()= runBlocking{
    var limit= 0.until(100)
    var d= File("intrare1.txt").writeText(limit.map{(1..100).random().toString()}.toString().replace("[","").replace("]",""))
   File("intrare.txt").writeText(limit.map{(1..100).random().toString()}.toString().replace("[","").replace("]",""))

    var x=listOf<Int>()
    var y=listOf<Int>()
    val corut=launch{
        x=File("intrare.txt").readText().split(",").map{it.trim(); it.toInt()}
    }
    val corut1=launch{
        y=File("intrare1.txt").readText().split(",").map{it.trim()}.map{it.toInt()}
    }
    delay(1)

    println(""+x.size+""+x+"\n"+y.size+y)

    var test=x.map{a -> val b=y.distinct().filter(){a*it == a+it*3};   Pair(a,b) }.filter{it.second.distinct().isNotEmpty()}

    println(test)
    println(""+x.size+""+x+"\n"+y.size+y)

    var c=Random().nextInt(100)
    println(c)


}