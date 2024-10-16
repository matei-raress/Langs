import Pizza.Pizza;
import PizzaStore.ChicagoStore.ChicagoPizzaStore;
import PizzaStore.NewYorkStore.NewYorkPizzaStore;
import PizzaStore.PizzaType;

public class Main {

    public static void main(String[] args) {
        Pizza[] vect = new Pizza[5];
        vect[0] = (new NewYorkPizzaStore()).CreatePizza(PizzaType.Cheese);
        vect[1] = (new NewYorkPizzaStore()).CreatePizza(PizzaType.Pepperoni);
        vect[2] = (new ChicagoPizzaStore()).CreatePizza(PizzaType.Clam);
        vect[3] = (new NewYorkPizzaStore()).CreatePizza(PizzaType.Clam);
        vect[4] =  (new ChicagoPizzaStore()).CreatePizza(PizzaType.Pepperoni);
        for (int i = 0; i < 5; i++) {
            System.out.println(vect[i]);
        }


    }
}
