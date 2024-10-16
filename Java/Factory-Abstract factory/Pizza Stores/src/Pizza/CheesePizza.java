package Pizza;

import IngredientFactory.Cheese.ICheese;
import IngredientFactory.Clams.IClams;
import IngredientFactory.Dough.IDough;
import IngredientFactory.IPizzaIngredientFactory;
import IngredientFactory.Pepperoni.IPepperoni;
import IngredientFactory.Sauce.ISauce;
import IngredientFactory.Veggies.IVeggies;
import PizzaStore.PizzaType;

public class CheesePizza extends Pizza{
    public CheesePizza(IPizzaIngredientFactory ingredientFactory, String name){
         this.name=name ;
         cheese=ingredientFactory.CreateCheese()  ;
         dough=ingredientFactory.CreateDough() ;
         sauce=ingredientFactory.CreateSouce() ;
         veggies=ingredientFactory.CreateVegies() ;

    }
    public void Prepare(){
        String a="" ;
        for(int i=0; i< veggies.length;i++ ){a+=veggies[i].GetName()+",";};
        System.out.println("S-a preparat pizza "+name+" cu : "+cheese.GetName()+","+dough.GetName()+","+sauce.GetName()+","+ a + ".") ;
    }

    @Override
    public Pizza CreatePizza(PizzaType tip) {
        return null;
    }
}
