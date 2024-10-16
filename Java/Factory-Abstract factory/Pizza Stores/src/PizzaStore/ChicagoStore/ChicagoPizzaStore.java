package PizzaStore.ChicagoStore;

import IngredientFactory.ChicagoPizzaIngredientFactory;
import Pizza.*;
import PizzaStore.PizzaType;

public class ChicagoPizzaStore extends Pizza{
    @Override
    public Pizza CreatePizza(PizzaType tip)
    {
        ChicagoPizzaIngredientFactory chig= new ChicagoPizzaIngredientFactory() ;
        switch(tip)
        {
            case Cheese:
                CheesePizza a=new CheesePizza(chig,"ChicagoCheese") ;
                a.Prepare();
                return a;
            case Pepperoni:
                PepperoniPizza b=new PepperoniPizza(chig,"ChicagoPepperoni") ;
                b.Prepare();
                return b;
            default:
                ClamPizza c=new ClamPizza(chig,"ChicagoClam") ;
                c.Prepare();
                return c;
        }

    }
}
