package org.example.Factory

import org.example.Handler.CEOHandler
import org.example.Handler.ExecutiveHandler
import org.example.Handler.Handler
import org.example.Handler.ManagerHandler

class EliteFactory : AbstractFactory() {
    override fun getHandler(handler: String): Handler {
        return if(handler == "CEO")
            CEOHandler()
        else if(handler == "Executive")
            ExecutiveHandler()
        else
            ManagerHandler()
    }
}