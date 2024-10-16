package PizzaStore.NewYorkStore;

import IngredientFactory.ChicagoPizzaIngredientFactory;
import IngredientFactory.NewYorkPizzaIngredientFactory;
import Pizza.*;
import PizzaStore.PizzaType;

public class NewYorkPizzaStore extends Pizza{
    public Pizza CreatePizza(PizzaType tip)
    {
        NewYorkPizzaIngredientFactory york= new NewYorkPizzaIngredientFactory() ;
        switch(tip)
        {
            case Cheese:
                CheesePizza a=new CheesePizza(york,"NewYorkCheese") ;
                a.Prepare();
                return a;
            case Pepperoni:
                PepperoniPizza b=new PepperoniPizza(york,"NewYorkPepperoni") ;
                b.Prepare();
                return b;
            default:
                 ClamPizza c=new ClamPizza(york,"NewYorkClam") ;
                c.Prepare();
                return c;
        }

    }
}
