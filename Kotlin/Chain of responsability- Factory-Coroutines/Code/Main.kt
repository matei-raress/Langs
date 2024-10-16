

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.example.Factory.FactoryProducer
import org.example.Handler.CEOHandler


fun main(args: Array<String>) {

    val factoryProducer = FactoryProducer()
    val happyFactory = factoryProducer.getFactory("happy")
    val eliteFactory = factoryProducer.getFactory("elite")

    var ceo1 = eliteFactory.getHandler("CEO")
    var ceo2 = eliteFactory.getHandler("CEO")


    var exec1 = eliteFactory.getHandler("Executive")
    var exec2 = eliteFactory.getHandler("Executive")

    var manager1 = eliteFactory.getHandler("Manager")
    var manager2 = eliteFactory.getHandler("Manager")

    var happy1 = happyFactory.getHandler("HappyWorker")
    var happy2 = happyFactory.getHandler("HappyWorker")



    ceo1.setNext(exec1,null)
    exec1.setNext(manager1,exec2)
    manager1.setNext(happy1,manager2)
    happy1.setNext(happy2,null)
    happy2.setNext(manager2,null)
    manager2.setNext(exec2,null)
    ceo2.setNext(ceo1,null)

    runBlocking{
        launch{
            ceo1.handleRequest("Request - 1: Mesaj pt CEO")
            ceo1.handleRequest("Request - 4: Mesaj pt worker")
        }
    }

}
