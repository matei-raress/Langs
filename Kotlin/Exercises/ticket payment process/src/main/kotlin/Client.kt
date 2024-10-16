class Client(Cash:Double,Cont:BankAccount,Demand:Cerere) {
    private var cash=Cash
    private var cont=Cont
    private var cerere=Demand

    public fun getCash():Double{
        return this.cash
    }
    public fun getCont():BankAccount{
        return this.cont
    }
    public fun getCerere():Cerere{
        return this.cerere
    }
    public fun UpdateCash(nr:Double){
        this.cash=this.cash-nr
    }

}