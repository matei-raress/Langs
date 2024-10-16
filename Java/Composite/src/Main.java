public class Main {
    public static void main(String[] args) {
        Tree html = InitHtml();
        System.out.println("HTML1");
        html.Afisare();
    }


    public static Tree InitHtml() {

        Folder html = new Folder("html");
        Paragraph title = new Paragraph("title", "Titlu");
        Folder body = new Folder("body");
        Paragraph paragraf = new Paragraph("p", "Text pus de test ");
        Lista lista = new Lista("li");
        Folder fold = new Folder("folder");
        Sectiune sect = new Sectiune();
        Lista lista2 = new Lista("li");
        Paragraph text = new Paragraph("p", "tag    ");

        html.Add(title);
        html.Add(body);
        body.Add(paragraf);
        body.Add(lista);
        lista.Add(text);
        lista.Add(fold);
        body.Add(sect);
        sect.Add(lista2);
        lista2.Add(text);
        return html;

    }
}