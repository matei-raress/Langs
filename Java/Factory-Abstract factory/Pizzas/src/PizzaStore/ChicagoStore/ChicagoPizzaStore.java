package PizzaStore.ChicagoStore;

import Pizza.ChicagoStyle.ChicagoStyleCheesePizza;
import Pizza.ChicagoStyle.ChicagoStylePepperoniPizza;
import Pizza.*;
import PizzaStore.PizzaStore;

public class ChicagoPizzaStore extends PizzaStore
{
    @Override
    public Pizza CreatePizza(PizzaType tip)
    {
        switch(tip)
        {
            case Cheese:
                return new ChicagoStyleCheesePizza();
            case Pepperoni:
                return new ChicagoStylePepperoniPizza();
            default:
                return null;
        }

    }
}