package org.example.Factory

import org.example.Handler.Handler

abstract class AbstractFactory {
    abstract fun getHandler(handler: String): Handler
}