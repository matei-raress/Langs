import java.util.*
import java.util.Scanner

fun main(args: Array<String>) {

    var film:Film=Film("Avatar",100,14.30,20.00)
    var cerere:Cerere=Cerere("Avatar",3,14.30)
    var data:Date=Date()
    var cont:BankAccount=BankAccount(100.00,"0893755328904",data,876,"Matei")
    var client:Client=Client(100.00,cont,cerere)

    val input=Scanner(System.`in`)


   if(ProgramareFilm(client,film).check()){ // verificare daca cererea e conform cerintelor filmului

     println("Platiti cash sau card")
       var case=readLine()

           when (case) {
               "cash" -> if (CashPayment(client.getCash()).pay(ProgramareFilm(client, film).getPrice())) {
                   client.UpdateCash(ProgramareFilm(client, film).getPrice())
                   film.UpdateSeats(client.getCerere().getSeats())
               }
               "card" -> if (CardPayment(client.getCont()).pay(ProgramareFilm(client, film).getPrice())) {
                   client.getCont().updateAmount(ProgramareFilm(client, film).getPrice())
                   film.UpdateSeats(client.getCerere().getSeats())
               }

               else -> println("La revedere")
           }



   }

}