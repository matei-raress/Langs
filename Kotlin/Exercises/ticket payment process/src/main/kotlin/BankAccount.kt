import java.util.*

class BankAccount(availableamount : Double, cardnumber : String, expirationdate : Date, cvvcode : Int, username : String){
    private var availableAmount = availableamount
    private var cardNumber = cardnumber
    private var experiationDate = expirationdate
    private var cvvCode = cvvcode
    private var userName = username

    public fun updateAmount(value : Double):Boolean{
       if(availableAmount>=value) {
           availableAmount =availableAmount- value
            return true
       }
        println("Fonduri insuficiente")
        return false
    }

}