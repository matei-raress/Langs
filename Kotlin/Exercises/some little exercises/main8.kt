//memento design pattern on a plant
data class Memento(var lungime:Int, var latime:Int)

class CareTaker(){
    var lista=mutableListOf<Memento>()

    fun saveToList(mem:Memento){
        this.lista.add(mem)
    }
    fun get(id:Int):Memento{
        return this.lista.removeAt(id)
    }
}

class Plant(var lungime: Int, var latime: Int){

    var caretaker=CareTaker()

    fun grow(){
        lungime+=1
        latime+=1
    }

    fun saveState(){
        var mem=Memento(lungime,latime)
        caretaker.saveToList(mem)
    }

    fun getOldPlant(id:Int){
        var mem=caretaker.get(id)
        lungime=mem.lungime
        latime=mem.latime
    }
    fun show(){
        println(" "+lungime+"  "+latime)
    }

}


fun main(){
    var penis=Plant(1,1,)

    penis.show()
    penis.saveState()

    penis.grow()
    penis.grow()
    penis.show()
    penis.getOldPlant(0)
    penis.show()


}