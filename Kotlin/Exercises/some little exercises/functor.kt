
class UnFlatMap<T>(val list: List<T>){
    fun map():List<Pair<T,T>> {
        return list.zipWithNext().filterIndexed(){index, pair -> index%2==0 }
    }
}

fun main() {
    var lista= listOf<List<Int>>(listOf<Int>(1,2),listOf<Int>(3,4),listOf<Int>(5,6)).flatMap { it }
    var a=mutableListOf(1, 2, 3, 4)

    println(UnFlatMap(a).map())


}
