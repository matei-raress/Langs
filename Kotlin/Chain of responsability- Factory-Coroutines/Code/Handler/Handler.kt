package org.example.Handler


interface  Handler {
    suspend fun handleRequest(messageTBP: String){}
    fun setNext(next1: Handler?,next2:Handler?){}
}