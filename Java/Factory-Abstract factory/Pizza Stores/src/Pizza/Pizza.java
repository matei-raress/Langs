package Pizza;


import IngredientFactory.Cheese.ICheese;
import IngredientFactory.Clams.IClams;
import IngredientFactory.Dough.IDough;
import IngredientFactory.IPizzaIngredientFactory;
import IngredientFactory.Pepperoni.IPepperoni;
import IngredientFactory.Sauce.ISauce;
import IngredientFactory.Veggies.IVeggies;
import PizzaStore.PizzaType;

public abstract class Pizza {
    protected String name ;
    protected ICheese cheese ;
    protected IClams clams ;
    protected IDough dough ;
    protected IPepperoni pepperoni ;
    protected ISauce sauce ;
    protected IVeggies[] veggies ;
    protected String processRecord ;
    protected IPizzaIngredientFactory ingredientFactory ;

    public Pizza() {}
    public String GetName()
    {
        return "name";
    }
    public String GetProcessRecord()
    {
        return "Process";
    }
    public void Prepare() {}
    public void Bake(){}
    public void Cut() {}
    public void Box() {}

    public abstract Pizza CreatePizza(PizzaType tip);
}
