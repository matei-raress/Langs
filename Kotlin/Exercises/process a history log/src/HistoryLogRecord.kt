import java.util.*

class HistoryLogRecord(timestamp: Date, command:String ) :Comparable<HistoryLogRecord>{
    var timeStamp= timestamp
    var Command=command

    override fun compareTo(a: HistoryLogRecord): Int {
        return this.timeStamp.compareTo(a.timeStamp)
    }

}