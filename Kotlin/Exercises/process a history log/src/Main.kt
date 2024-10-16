import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import kotlin.collections.HashMap

fun <T : Comparable<T>> greater (one: T, two : T) : T{
    if(one.compareTo(two) >= 0)
        return one
    return two
}

fun searchReplace(search: HistoryLogRecord, replace: HistoryLogRecord , map :HashMap<Date,HistoryLogRecord> ){
    for((key,value) in map){
        if(value == search){
            map[key] = replace
        }
    }
}

fun main(){

    val history= File("history.LOG").readLines()
    var timestamp=Date()
    var map=hashMapOf<Date,HistoryLogRecord>()
    var command=String()

    var lastLines = mutableListOf<String>()

    lastLines=history.toMutableList()

    var i=0
    if(history.lastIndex>=250){
        var n=history.lastIndex
        var index=n-250
        
        while(index<n){
            lastLines.add(i,history.get(index))
            index++
            i++
        }
    }

    lastLines.forEach {
        if (it.contains("Start-Date: ")) {
            var line = it.split(" ")
            var date = line[1] + " " + line[2]
            timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date) // .format(date)
        }

        if(it.contains("Commandline:")){
            var line = it.split(":")
            command = line[1]
            map[timestamp] = HistoryLogRecord(timestamp,command)
        }
    }

    timestamp= SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2000-00-00 00:00:00")
    var max = HistoryLogRecord(timestamp, command)
    val k=map.keys.first()
    max= map[k]!!
    for ((key, value) in map) {
            max = greater(max,value)
    }

    timestamp= SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-10-22 22:00:00")
    val search = HistoryLogRecord(timestamp, command)
    timestamp= SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-10-23 22:00:00")
    val replace = HistoryLogRecord(timestamp, command)
    searchReplace(search, replace , map )

}

