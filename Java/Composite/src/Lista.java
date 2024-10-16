import java.util.ArrayList;
import java.util.List;

public class Lista extends Tree {
    private List<Tree> item = new ArrayList();

    public Lista(String name) {
        this.name = name;
    }

    public void Add(Tree item) {
        item.SetLevel(this.GetLevel() + 1);
        this.item.add(item);
    }

    public void Afisare() {
        for (int i = 0; i < level; i++) {
            System.out.print("   ");
        }
        System.out.println("<li>");
        for (Tree item : this.item) {
            for (int i = 0; i < level; i++) {
                System.out.print("   ");
            }
            System.out.println("<ul>");
            item.Afisare();
            for (int i = 0; i < level; i++) {
                System.out.print("   ");
            }
            System.out.println("</ul>");
        }
        for (int i = 0; i < level; i++) {
            System.out.print("   ");
        }
        System.out.println("</li>");

    }
}
