interface PaymentMethod {
    public fun pay(fee:Double):Boolean
}

class CashPayment(clientCash:Double): PaymentMethod{ //Vede daca se poate plati cash
    private var avaibleAmount=clientCash

   override fun pay(fee:Double):Boolean{
        if(this.avaibleAmount >= fee)
        {
            return true
        }
       println("Nu ai destui cash")
        return false
    }
}

class CardPayment(bank:BankAccount): PaymentMethod{ //Vede daca se poate plati card
    private var bankAccount=bank

    override fun pay(fee:Double):Boolean{

        return bankAccount.updateAmount(fee)
    }
}