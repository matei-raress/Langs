package org.jsoup

import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class Element(title: String, link: String, description: String, pubDate: String) {
    var title: String
    var link: String
    var description: String
    var pubDate: String

    init {
        this.title = title
        this.link = link
        this.description = description
        this.pubDate = pubDate
    }

    fun afisare() {
        println(title + " : " + link)
    }
}


fun main(args: Array<String>) {
    val http: String = "http://rss.cnn.com/rss/edition.rss"
    val html: Document = Jsoup.connect(http).get()  //document cu html
    val itemlist: Elements = html.select("item")
    val elemente = mutableListOf<Element>()
    for (item in itemlist) {

        elemente.add(
            Element(
                item.select("title").text(),
                item.select("link").text(),
                item.select("description").text(),
                item.select("pubDate").text()
            )
        )
    }
    println("Title : link")
    for (item in elemente)
        item.afisare()
}

