public class Bold extends Decorator {
    public Bold(String name, String _content) {
        this.name = name;
        this.content = _content;

    }

    public void Afisare() {

        for (int i = 0; i < level; i++) {
            System.out.print("   ");
        }
        System.out.print("<" + name + "> ");
        System.out.print("Text scris normal | " + "\u001B[1m" + content + "\u001B[0m  ");
        System.out.println("</" + name + ">");
    }
}