//testing functional calculus on adt
fun main(){
    var a =listOf(1,23,4,54,2)
    var b= listOf(2,3,23,52,963,2)
    var c=a+b
    println(c)

        println(a.map{lista1 -> b.filter{lista1 == it }.distinct() }.distinct().filter{it.isNotEmpty()})
        println( a.map{elem1-> b.map{Pair(elem1,it)} }.flatten() )

}