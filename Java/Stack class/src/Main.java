class Stiva {
    public int[] vect;
    public int varf;

    Stiva() {
        vect = new int[100];
        varf = -1;
    }

    Stiva(int k) {
        vect = new int[k];
        varf = -1;
    }

    void Push(int a) {
        if (varf < vect.length) {
            varf = varf + 1;
            vect[varf] = a;
        } else {
            System.out.print("Stiva plina");
        }
    }

    int Pop() {
        if (!isEmpty()) {
            int a = vect[varf];
            varf--;
            return a;
        }
        return 0;
    }

    boolean isEmpty() {
        if (varf == -1) return true;
        return false;
    }

    public static void main() {
        Stiva s = new Stiva(3);
        s.Push(5);
        s.Push(43);
        s.Push(46);

        int out = s.Pop();
        System.out.print("Indexul varfului  : " + s.varf + " cu valorea " + out);
    }


}

public class Main {
    public static void main(String[] args) {
        Stiva s = new Stiva();
        s.main();
    }

}
