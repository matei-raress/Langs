package org.example.Handler


import jdk.nashorn.internal.objects.Global
import kotlinx.coroutines.*

class CEOHandler() : Handler {
     var next1: Handler? =null
     var next2: Handler? =null
    override suspend fun handleRequest(messageTBP: String)  {

        var incoming:List<String>
        var testIncoming: String? =null
        var finish: String? =null
        if(messageTBP.contains("Response")) {
             incoming = messageTBP.split("Response -")
             testIncoming = incoming[0]
             finish = incoming[1]
        }

        var message = messageTBP.split(":")
        var priority = message[0]
        var request  = message[1]
        val response:String= "Response -$request"


        if(testIncoming=="1"){
            GlobalScope.launch{
                print("Mesaj ajuns la CEO$finish")
            }
            return
        }
        else if(priority.contains("1") ){
            GlobalScope.launch{
                print("Mesaj ajuns la CEO$response")
            }
            return
        }
        else if(priority.contains("Response -")){
            GlobalScope.launch{
            next1!!.handleRequest("1$messageTBP")
            }
            delay(500)
            return
        }
        else{
            GlobalScope.launch{
                next1!!.handleRequest(messageTBP)
            }
            delay(500)
            return
        }
}
    override fun setNext(next1: Handler?, next2: Handler?){
        this.next1=next1
        this.next2=next2
    }
}