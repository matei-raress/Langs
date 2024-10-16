class Film(Name:String,Seats:Int,Hour:Double,Price:Double):GetData{
    private var price=Price
    private var name=Name
    private var seats=Seats
    private var hour=Hour

    public fun UpdateSeats(taken:Int){
        this.seats=this.seats-taken
    }
    override public fun getName():String{
        return this.name
    }
    override public fun getSeats():Int{
        return this.seats
    }
    override public fun getHour():Double{
        return this.hour
    }
    public fun getPrice():Double{
        return this.price
    }
}
