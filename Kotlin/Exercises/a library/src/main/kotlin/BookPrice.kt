class BookPrice(data: Content, price: Double) : Book(data) {
    private var data: Content = data
    private var price: Double = price

    public fun SetPrice(nou: Double) {
        price = nou
    }

    public fun GetPrice(): Double {
        return price
    }

    override public fun toString(): String {
        var str: String = super.toString()
        str = str.substring(0, str.length - 2)
        str += ", price: " + price + ")\n"
        return str
    }
}