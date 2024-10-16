public class Main
{
    public static void main(String[] args)
    {
        Decorator html = InitHtml();
        System.out.println("HTML1");
        html.Afisare();
    }
    private static Decorator InitHtml()
    {

        Folder html = new Folder("html");
        File title = new File("title", "Titlul fisierului");
        Folder body = new Folder("body");

        Italic italic = new Italic("i", "Text scris cu italic");
        Bold bold = new Bold("b", "Text scris cu bold");
        UnderLine under = new UnderLine("u", "Text subliniat");

        html.Add(title);
        html.Add(body);
        body.Add(italic);
        body.Add(bold);
        body.Add(under);


        return html;

    }
}
