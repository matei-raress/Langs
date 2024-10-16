fun main() {
    var content: Content = Content("Matei", "O capra a adormit sub prun", "Capra adormita", "O capra")
    var Carte = Book(content)

    var content1: Content = Content("Rares", "Un urs a fugit din inchisoare", "Ursul fugar", "Alt urs")
    var Carte1 = Book(content1)
    var carti: MutableSet<Book> = mutableSetOf()
    carti.add(Carte)
    carti.add(Carte1);

    LibraryPrinter().printBooksRaw(carti);
    println();
    LibraryPrinter().printHTML(carti);
    println();
    LibraryPrinter().printJSON(carti);

    var content2: Content = Content("Carmen", "Un vultur sta pe stanca", "Vultur plesuv", "Le vultur")
    var Carte2 = BookPrice(content1, 25.3)
    println(Carte2.toString())

}