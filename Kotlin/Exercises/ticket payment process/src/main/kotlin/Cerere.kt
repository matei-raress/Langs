class Cerere(MovieName:String,Seats:Int,Hour:Double):GetData {
    private var name=MovieName
    private var seats=Seats
    private var hour=Hour

    override public fun getName():String{
        return this.name
    }
    override public fun getSeats():Int{
        return this.seats
    }
   override public fun getHour():Double{
        return this.hour
    }
}