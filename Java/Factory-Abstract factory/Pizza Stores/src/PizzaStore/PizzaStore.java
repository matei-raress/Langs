package PizzaStore;

import Pizza.Pizza;

public abstract class PizzaStore {
    protected abstract Pizza CreatePizza(PizzaType tip) ;

    public Pizza OrderPizza(PizzaType tip)    {
        return CreatePizza(tip);
    }
}