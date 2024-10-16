package org.example.Factory

class FactoryProducer {
    fun getFactory(choice: String): AbstractFactory{
        return if(choice == "happy"){
            HappyWorkerFactory()
        } else{
            EliteFactory()
        }

    }
}