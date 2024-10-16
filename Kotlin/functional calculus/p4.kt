// create a functor for a collection of type MutableMap which can process the values: first map will put "Test" in front of each value, second map will caall the function toPascalCase
class CollectionFunctor(val mutableMap: MutableMap<Int,String>) {
    fun map(function: (MutableMap<Int,String>) -> MutableMap<Int,String>): CollectionFunctor {
        return CollectionFunctor(function(mutableMap))
    }
}

fun addTest(mutableMap: MutableMap<Int,String>) : MutableMap<Int,String>{
    mutableMap.forEach{ key, value -> mutableMap[key] = "Test " + value }
    return mutableMap
}

fun ToPascalCase(map: MutableMap<Int,String>) : MutableMap<Int,String> {
    map.forEach { key, value -> map[key]=value.split("").map{it.capitalize()}.joinToString(separator="") }
    return map
}

fun main() {
    var myMap = mutableMapOf<Int,String>(1 to "Text cu spatiu", 2 to "Alt text dar cu mai mult spatiu", 3 to "Simplu spatios")
    println(myMap)
    println(CollectionFunctor(myMap).map(::addTest).map(::ToPascalCase).mutableMap)

}




