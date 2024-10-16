//read from a file and crypt the the words with length between 4 and 7 with Caesar code
fun String.toCeasar(offset: Int): String{
    var word = ""
    var noZ= this.lowercase()
    for( c in noZ){
        if((c.code + offset ) > 'z'.code){
           noZ=noZ.replace(c,'a',true)
        }
    }

    word += noZ.lowercase().map{(it.code + offset).toChar()}
    return word
}

fun main() {
    //val text = File("text.txt").readText().split(" ")
    var list = mutableListOf("Cuvinte","care","nu","gasesc","rost","pe","planeta","asta","decat","aiciea","sternocleidomastoidiat")
    val offset = 4;
    var plist = list.filter{it.length>=4 && it.length<=7}.map{it.toCeasar(offset)}
    println(plist)


}