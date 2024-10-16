package org.example.Handler

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HappyWorkerHandler(var next1:Handler?=null):Handler {
    override suspend fun handleRequest(messageTBP: String){
        var message = messageTBP.split(":")
        var priority = message[0]
        var request  = message[1]


        if(priority.contains("4")){
            val response:String= "Response -$request"
            GlobalScope.launch{
                next1!!.handleRequest(response)
            }
            delay(500)
            return

        }
        else if(priority.contains("Request")){
            val response:String= "Response -(non-taken)$request"
            GlobalScope.launch{
                next1!!.handleRequest(response)
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
    }}