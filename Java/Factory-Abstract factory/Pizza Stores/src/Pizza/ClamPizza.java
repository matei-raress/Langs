package Pizza;

import IngredientFactory.IPizzaIngredientFactory;
import PizzaStore.PizzaType;

public class ClamPizza extends Pizza {
    public ClamPizza(IPizzaIngredientFactory ingredientFactory, String name){
        this.name=name ;
        clams=ingredientFactory.CreateClams()  ;
        dough=ingredientFactory.CreateDough() ;
        sauce=ingredientFactory.CreateSouce() ;
        veggies=ingredientFactory.CreateVegies() ;

    }
    public void Prepare(){
        String a="" ;
        for(int i=0; i< veggies.length;i++ ){a+=veggies[i].GetName()+",";};
        System.out.println("S-a preparat pizza "+name+" cu : "+clams.GetName()+","+dough.GetName()+","+sauce.GetName()+","+ a + ".") ;
    }

    @Override
    public Pizza CreatePizza(PizzaType tip) {
        return null;
    }
}
