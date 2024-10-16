abstract class AbstractCaroserie {
    public abstract void operatiaC1();
    public abstract void operatieC2();
}
class Caroserie1 extends AbstractCaroserie{
    Caroserie1(String arg)
    {System.out.println("Model caroserie1: "+arg);}
    // Implementare cod pentru:
    public void operatiaC1(){ };
    public void operatieC2() { };
}
class Caroserie2 extends AbstractCaroserie{
    Caroserie2(String arg)
    {System.out.println("Model caroserie2: "+arg);}
    // Implementare cod pentru:
    public void operatiaC1() { };
    public void operatieC2() { };
}
