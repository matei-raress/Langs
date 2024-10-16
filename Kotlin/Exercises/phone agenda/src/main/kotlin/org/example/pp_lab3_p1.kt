import java.util.Scanner

class Birth(val year: Int, val month: Int, val day: Int) {
    override fun toString(): String {
        return "($day.$month.$year)"  //return string
    }
}

class Contact(val name: String, var phone: String, val birthDate: Birth) {
    fun print() {
        println("name: $name, Mobile: $phone, Date: $birthDate")
    }
}

fun main(args: Array<String>) {
    val agenda = mutableListOf<Contact>()

    agenda.add(Contact("Mihai", "0744321987", Birth(1900, 11, 25)))
    agenda += Contact("George", "0761332100", Birth(2002, 3, 14))
    agenda += Contact("Liviu", "0231450211", Birth(1999, 7, 30))
    agenda += Contact("Popescu", "0211342787", Birth(1955, 5, 12))
    for (persoana in agenda) {
        persoana.print()
    }

    val input = Scanner(System.`in`)

    print("Cautare dupa numele: ")
    val caut: String = input.nextLine()

    var numegasit: Boolean = false
    var i: Int

    for (i in 0..3) // sau for(i in 0..agenda.count())
    {
        if (agenda[i].name == caut) {
            numegasit = true
            print("Numele a fost gasit, actualizati nr de telefon :")
            agenda[i].phone = input.nextLine()
            print("Noul numar este: " + agenda[i].phone)
        }
    }


    if (!numegasit) {
        print("Nu s-a gasit numele")
    }
}


