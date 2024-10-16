class LibraryPrinter() {

    public fun printBooksRaw(lista: MutableSet<Book>) {
        for (book in lista) {
            print(book.toString())
        }
    }

    public fun printHTML(lista: MutableSet<Book>) {
        for (book in lista) {
            print("<html>\n")
            print("<head>\n")
            print("<title>\"" + book.getName() + "\"</title>\n")
            print("</head>\n")
            print("<body>\n")
            print("<h1>\"" + book.getName() + "\" </h1>\n")
            print("<h2>\"" + book.getAuthor() + "\" </h2>\n")
            print("<p1>\"" + book.getContent() + "\" </p1>\n")
            print("<p1>\"" + book.getPublisher() + "\" </h1>\n")
            print("</body>\n")
            print("</html>\n")
            println();
        }
    }

    public fun printJSON(lista: MutableSet<Book>) {
        println("[")
        for (index in lista.indices) {
            println("{")
            println("\"bookName\": \"" + lista.elementAt(index).getName() + "\",")
            println("\"author\": \"" + lista.elementAt(index).getAuthor() + "\",")
            println("\"publisher\": \"" + lista.elementAt(index).getPublisher() + "\",")
            println("\"Text\": \"" + lista.elementAt(index).getContent() + "\",")
            println("},")
        }
        println("]")
        println();
    }
}