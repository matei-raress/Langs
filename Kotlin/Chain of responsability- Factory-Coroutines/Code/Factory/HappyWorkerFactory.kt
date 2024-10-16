package org.example.Factory

import org.example.Handler.Handler
import org.example.Handler.HappyWorkerHandler

class HappyWorkerFactory : AbstractFactory() {
    override fun getHandler(handler: String): Handler {
        return HappyWorkerHandler()
    }
}