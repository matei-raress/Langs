package PizzaStore.NewYorkStore;

import Pizza.*;
import Pizza.NewYorkStyle.NewYorkStyleCheesePizza;
import Pizza.NewYorkStyle.NewYorkStylePepperoniPizza;
import PizzaStore.PizzaStore;

public class NewYorkPizzaStore extends PizzaStore {

    @Override
    protected Pizza CreatePizza(PizzaType tip) {
        switch(tip){
            case Cheese: return new NewYorkStyleCheesePizza() ;
            case Pepperoni: return new NewYorkStylePepperoniPizza() ;
        }

        return null;
    }
}
