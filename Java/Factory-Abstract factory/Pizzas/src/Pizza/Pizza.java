package Pizza;

public abstract class Pizza {
    public String name;
    public int price;
    public int size;
    public Pizza(){ }
    @Override
    public String toString()
    {
        return "Pizza " +name +" are pretul "+ price + " si  marimea  "+ size + " cm";
    }

}

