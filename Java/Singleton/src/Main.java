import java.util.*;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Queue<File> coada = new LinkedList<File>();


        coada.add(new File ("Fisier 1 ", 6));
        coada.add(new File ("Fisier 2 ", 12));
        coada.add(new File ("Fisier 3 ", 15));
        coada.add(new File ("Fisier 4 ", 18));
        coada.add(new File ("Fisier 5 ", 20));

        while(!coada.isEmpty())
        {

            Download download = Download.GetInstance(coada.peek());
            coada.remove();
            Download.Reset();
        }
        System.out.println("Timpul total este "+Download.totalTime);

    }
}
