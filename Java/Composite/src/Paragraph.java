public class Paragraph extends Tree {
    public Paragraph(String name, String content) {
        this.name = name;
        this.content = content;

    }

    public void Afisare() {
        for (int i = 0; i < level; i++) {
            System.out.print("   ");
        }
        System.out.print("<" + name + "> ");
        System.out.print(content + " ");
        System.out.println("</" + name + ">");
    }
}
