package IngredientFactory;

import IngredientFactory.Cheese.ICheese;
import IngredientFactory.Cheese.ReggianoICheese;
import IngredientFactory.Clams.FrozenClams;
import IngredientFactory.Clams.IClams;
import IngredientFactory.Dough.IDough;
import IngredientFactory.Dough.ThinCrustDough;
import IngredientFactory.Pepperoni.IPepperoni;
import IngredientFactory.Pepperoni.SlicedPepperoni;
import IngredientFactory.Sauce.ISauce;
import IngredientFactory.Sauce.PlumTomatoSauce;
import IngredientFactory.Veggies.*;

public class NewYorkPizzaIngredientFactory implements IPizzaIngredientFactory{

    public IDough CreateDough() {
        return new ThinCrustDough();
    }

    public ISauce CreateSouce() {
        return new PlumTomatoSauce();
    }

    public ICheese CreateCheese() {
        return new ReggianoICheese();
    }

    public IVeggies[] CreateVegies() {
        IVeggies[] v = new IVeggies[3];
        v[0]=new Onion();
        v[1]=new RedPepper();
        v[2]=new Spinach();
        return  v;
    }

    public IPepperoni CreatePepperoni() {
        return new SlicedPepperoni();
    }

    public IClams CreateClams() {
        return new FrozenClams();
    }
}
