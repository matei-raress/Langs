package org.example.Handler

import jdk.internal.org.objectweb.asm.Handle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ManagerHandler():Handler {
    var next1:Handler?=null
    var next2:Handler?=null
    override suspend fun handleRequest(messageTBP: String){

        var message = messageTBP.split(":")
        var priority = message[0]
        var request  = message[1]


        if(priority.contains("3")){
            val response:String= "Response -$request"
            GlobalScope.launch{
                next2!!.handleRequest(response)
            }
            delay(500)
            return

        }
        else {
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