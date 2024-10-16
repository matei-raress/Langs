import java.util.* ;

class IntSet extends BitSet {
    BitSet vect = new BitSet();

    public void add (int elem)
    {
        vect.set(elem, true);
    }
    public void remove(int elem)
    {
        vect.set(elem, false);
    }
    public boolean contains(int n) {
        if(vect.get(n)) return true ;
        return false ;
    }

    public String toString()
    {
        return vect.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        IntSet array = new IntSet() ;
        array.add(4) ;
        array.add(6) ;
        array.add(498) ;
        array.add(40) ;

        System.out.println(array.toString()) ;

        Integer a= Integer.valueOf(6) ;
        array.remove(a);

        System.out.println(array.toString()) ;

        if(array.contains(498)){
            System.out.print("Valoarea e aici") ;
        }
    }
}