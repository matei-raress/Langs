public class File {
    private String name;
    private double dim;
    File (String name, double dim)
    {
        this.name = name;
        this.dim = dim;

    }
    public String getname(){
        return name;
    }
    public double getSize()
    {
        return dim;
    }
}
