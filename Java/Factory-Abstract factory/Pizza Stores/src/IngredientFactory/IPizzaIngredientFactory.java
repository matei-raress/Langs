package IngredientFactory;

import IngredientFactory.Cheese.ICheese;
import IngredientFactory.Clams.IClams;
import IngredientFactory.Dough.IDough;
import IngredientFactory.Pepperoni.IPepperoni;
import IngredientFactory.Sauce.ISauce;
import IngredientFactory.Veggies.IVeggies;

public interface IPizzaIngredientFactory {
    public IDough CreateDough();
    public ISauce CreateSouce();
    public ICheese CreateCheese();
    public IVeggies[] CreateVegies();
    public IPepperoni CreatePepperoni();
    public IClams CreateClams();

}