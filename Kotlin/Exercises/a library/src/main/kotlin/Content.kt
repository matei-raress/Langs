class Content(author: String, text: String, name: String, publisher: String) : GetInfo {

    private var author: String = author
    private var text: String = text
    private var name: String = name
    private var publisher: String = publisher

    override fun getName(): String {
        return this.name
    }

    override fun getAuthor(): String {
        return this.author
    }

    override fun getPublisher(): String {
        return this.publisher
    }

    public fun getText(): String {
        return this.text
    }

    public fun setAuthor(author: String) {
        this.author = author
    }

    public fun setText(text: String) {
        this.text = text
    }

    public fun setName(name: String) {
        this.name = name
    }

    public fun setPublisher(publisher: String) {
        this.publisher = publisher
    }
}