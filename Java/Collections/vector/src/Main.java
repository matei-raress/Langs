import java.util.*;

//using a attribute to keep the data
class SetAsVector extends Vector {
    public Vector vect = new Vector();

    public boolean add(Object obiect) {
        if (vect.add(obiect)) return true;
        return false;
    }

    public boolean remove(Object obiect) {
        if (vect.remove(obiect)) return true;
        return false;
    }

    public boolean contains(Object obiect) {
        if (vect.contains(obiect)) return true;
        return false;
    }

    public String toString() {
        return vect.toString();
    }
}

//using the super class to contain the data
class SetAsVectorb extends Vector {
    public boolean add(Object obiect) {
        if (super.add(obiect)) return true;
        return false;
    }

    public boolean remove(Object obiect) {
        if (super.remove(obiect)) return true;
        return false;
    }

    public boolean contains(Object obiect) {
        if (super.contains(obiect)) return true;
        return false;
    }

    public String toString() {
        return super.toString();
    }
}

class SortedVector extends Vector{
    Vector vect=new Vector() ;

    public void  addElement(Object obiect){
        vect.add(obiect) ;
        Collections.sort(vect);
    }

    public void insertElementAtIndex(Object obiect, int n){
        vect.insertElementAt(obiect,n);
    }

    public String toString()
    {
        return vect.toString();
    }
}

public class Main {

    public static void main(String[] args) {
        SetAsVectorb array = new SetAsVectorb();
        array.add(4);
        array.add(6);
        array.add(498);
        array.add(40);

        System.out.println(array.toString());

        Integer a = Integer.valueOf(6);
        array.remove(a);

        System.out.println(array.toString());

        if (array.contains(498)) {
            System.out.print("Valoarea e aici");
        }

        //Using the sorted vector class
        SortedVector array = new SortedVector() ;
        array.addElement(4) ;
        array.addElement(6) ;
        array.addElement(498) ;

        System.out.println(array.toString()) ;
        array.addElement(40) ;

        System.out.println(array.toString()) ;
    }
}