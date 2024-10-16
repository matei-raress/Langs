class Library(books: MutableSet<Book>) {
    private var books = books

    public fun getBooks(): MutableSet<Book> {
        return books
    }

    public fun addBook(book: Book) {
        books.add(book)
    }

    public fun findAllByAuthor(author: String): MutableSet<Book> {
        var autori = mutableSetOf<Book>() //autori:MutableList<Book>=mutableListOf()
        for (book in books) {
            if (author.equals(book.getAuthor())) {
                autori.add(book)
            }
        }

        return autori
    }

    public fun findAllByName(name: String): MutableSet<Book> {
        var nume = mutableSetOf<Book>() //autori:MutableList<Book>=mutableListOf()
        for (book in books) {
            if (name.equals(book.getName())) {
                nume.add(book)
            }
        }
        return nume
    }

    public fun findAllByPublisher(publisher: String): MutableSet<Book> {
        var public = mutableSetOf<Book>() //autori:MutableList<Book>=mutableListOf()
        for (book in books) {
            if (publisher.equals(book.getPublisher())) {
                public.add(book)
            }
        }
        return public
    }
}