open class Book(data: Content) : GetInfo {
    private val data: Content = data

    public override fun getName(): String {
        return data.getName()
    }

    public override fun getAuthor(): String {
        return data.getAuthor()
    }

    public override fun getPublisher(): String {
        return data.getPublisher()
    }

    public fun getContent(): String {
        return data.getText()
    }

    public override fun toString(): String {
        var str: String =
            "Book(name: " + data.getName() + ", author = " + data.getAuthor() + ", publisher = " + data.getPublisher() + ", text: " + data.getText() + ")\n"
        return str
    }

    public fun hasAuthor(author: String): Boolean {
        return author.equals(data.getAuthor())
    }

    public fun hasTitle(title: String): Boolean {
        return title.equals(data.getName())
    }

    public fun isPublishedbBy(publisher: String): Boolean {
        return publisher.equals(data.getPublisher())
    }
}