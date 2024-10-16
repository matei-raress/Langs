package Pizza;

import Pizza.NewYorkStyle.NewYorkStylePepperoniPizza;
import PizzaStore.ChicagoStore.ChicagoPizzaStore;
import PizzaStore.NewYorkStore.NewYorkPizzaStore;
import PizzaStore.PizzaStore;

import static Pizza.PizzaType.Cheese;
import static Pizza.PizzaType.Pepperoni;

public class PizzaFactory {

    public Pizza creare(PizzaType tip, int size, int price ){

        PizzaStore chig= new ChicagoPizzaStore();
        PizzaStore york=new NewYorkPizzaStore();

        if(tip== Cheese)
        {
           if(size==10){
               return chig.OrderPizza(Cheese) ;
           }
           else{
               return york.OrderPizza(Cheese) ;
           }
        }
        else {
            if(size==10){
                return chig.OrderPizza(Pepperoni) ;
            }
            else{
                return york.OrderPizza(Pepperoni) ;
            }

        }


    };

}
