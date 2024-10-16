import java.util.ArrayList;
import java.util.List;

public class Folder extends Tree {
    private List<Tree> item = new ArrayList();

    public Folder(String name) {
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
        System.out.println("<" + this.name + ">");
        for (Tree item : this.item) {
            item.Afisare();
        }
        for (int i = 0; i < level; i++) {
            System.out.print("   ");
        }
        System.out.println("</" + this.name + ">");

    }
}
