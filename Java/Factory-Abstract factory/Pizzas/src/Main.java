import Pizza.Pizza;

import Pizza.* ;

public class Main {

    public static void main(String[] args)
    {
        Pizza[] vect = new Pizza[4];
        vect [0] = (new PizzaFactory()).creare(PizzaType.Cheese, 10,10);
        vect [1] = (new PizzaFactory()).creare(PizzaType.Cheese, 24,24);
        vect [2] = (new PizzaFactory()).creare(PizzaType.Pepperoni, 11,11);
        vect [3] = (new PizzaFactory()).creare(PizzaType.Pepperoni, 32,32);
        for (int i = 0; i <4; i++){
            System.out.println(vect[i]);
        }



    }
}


