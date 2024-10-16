import java.util.Scanner;

class FactoryMaker{
    private static AbstractFactory factory=null;
    static AbstractFactory getFactory(String opt){
        if(opt.equals("a")){
            factory = new Fabrica1();
        }
        else
        if(opt.equals("b")){
            factory = new Fabrica2();
        }
        return factory;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Numarul masinilor este: ");
        Scanner in = new Scanner(System.in);

        AbstractFactory[] pf = new AbstractFactory[5];
        AbstractMotoare motoare;
        AbstractCaroserie caroserie;

        for (int i = 0; i < 5; i++)
        {
            System.out.println("Selectati care este fabrica: a - pentru prima fabrica, b - pentru fabrica a doua ");
            String fact =in.nextLine();
            pf[i] = FactoryMaker.getFactory(fact);
            motoare = pf[i].CreazaMotor();
            caroserie = pf[i].CreazaCaroserie();
        }

    }
}
