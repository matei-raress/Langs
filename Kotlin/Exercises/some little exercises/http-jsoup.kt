package org.example

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.File
import java.util.*


fun testKhttpGetRequest(url: String) : String {
    val response = khttp.get(url)
    println("${response.statusCode}\t ${response.headers["Content-Type"]}")
    return response.text
}

fun testJsoup(source: String, url: String, baseURI: String?=null) {
    var htmlDocument: Document? = null
    htmlDocument = when(source) {
        "url" -> Jsoup.connect(url).get()
        "file" -> Jsoup.parse(File(url), "UTF-8", baseURI)
        "string" -> Jsoup.parse(url)
        else -> throw Exception("Unknown source")
    }
    println(htmlDocument.title())
    var imagini:Vector<String>
    //println(htmlDocument.body())
    val paragraphs: Elements=htmlDocument.select("img");

    //println(htmlDocument.body().getElementsByAttribute("src"))
    println(htmlDocument.body().getElementsByTag("img"))
    //imagini.addElement()

    println(htmlDocument.body().getElementById("first"))


}
fun main() {
    val projectPath: String = System.getProperty("user.dir")
    val htmlPath: String = "${projectPath}/src/main/resources/example.html"
    val url: String = "https://khttp.readthedocs.io/en/latest/"
  //  val htmlContent: String = testKhttpGetRequest(url)
    println("*".repeat(100))
//    testJsoup("file", htmlPath, "mike.tuiasi.ro")

        var a=Jsoup.connect(url).get().body()
    var b =khttp.get(url).text
    println(a)
    println("*".repeat(100))
    println(b)
}


