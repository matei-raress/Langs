import java.awt.Point
import java.lang.Math.sqrt
import kotlin.math.pow

// given an n that represents the number of points that resemple an polygon, calculate the perimeter
fun main() {
    val list = mutableListOf(4, 0,0,  0,1, 1,1, 1,0)
    val nr =list.dropLast(list.size-1)
    list.removeAt(0)
    println("Poligon cu $nr laturi:")


    val first2=  list.dropLast(list.size-2)
    val last2=  list.drop(list.size-2)
    val extrems=first2+last2


    val correctExtrems=extrems.zipWithNext().toMutableList().filterIndexed{index,pair->index%2==0}
    val coordonates =correctExtrems.map{ Point(it.first,it.second)}
    val coordonatesPair= coordonates.zipWithNext().toMutableList()


    val correctPair = list.zipWithNext().toMutableList().filterIndexed { index, s -> index % 2 == 0 }
    val coordonatesList =correctPair.map{ Point(it.first,it.second)}
    var finalPair = coordonatesList.zipWithNext().toMutableList()

    finalPair= (finalPair+coordonatesPair) as MutableList<Pair<Point, Point>>

    println(finalPair)

    val distances=finalPair.map{ a -> sqrt(((a.second.x - a.first.x).toDouble().pow(2)+(a.second.y - a.first.y).toDouble().pow(2)))  }

    println(distances)

    val perimeter=distances.reduce { it, a -> it + a }
    println("Perimeter: $perimeter")

}