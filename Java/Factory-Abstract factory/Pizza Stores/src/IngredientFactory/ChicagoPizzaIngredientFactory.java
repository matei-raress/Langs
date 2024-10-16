package IngredientFactory;

import IngredientFactory.Cheese.ICheese;
import IngredientFactory.Cheese.Mozzarella;
import IngredientFactory.Clams.FreshClams;
import IngredientFactory.Clams.IClams;
import IngredientFactory.Dough.IDough;
import IngredientFactory.Dough.ThickCrustDough;
import IngredientFactory.Pepperoni.GrilledPepperoni;
import IngredientFactory.Pepperoni.IPepperoni;
import IngredientFactory.Sauce.ISauce;
import IngredientFactory.Sauce.MarinaraSouce;
import IngredientFactory.Veggies.*;

public class ChicagoPizzaIngredientFactory implements IPizzaIngredientFactory{

    public IDough CreateDough() {
        return new ThickCrustDough();
    }

    public ISauce CreateSouce() {
        return new MarinaraSouce();
    }

    public ICheese CreateCheese() {
        return new Mozzarella();
    }

    public IVeggies[] CreateVegies() {
        IVeggies[] v = new IVeggies[4];
        v[0]=new BlackOlives();
        v[1]=new EggPlant();
        v[2]=new Garlic();
        v[3]=new Mushroom();
        return  v;
    }

    public IPepperoni CreatePepperoni() {
        return new GrilledPepperoni();
    }

    public IClams CreateClams() {
        return new FreshClams();
    }
}
