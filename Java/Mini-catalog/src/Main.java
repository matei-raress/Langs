//package java.lang ;
import java.lang.*;

public class Main {
    public static void main(String[] args) {
        StudentClass sc = new StudentClass(10);
        sc.Add(new Student("Gica", 19, 9));
        sc.Add(new Student("Andrei", 20, 7));
        sc.Add(new Student("Matei", 21, 10));
        sc.Add(new Student("Kalin", 5, 5));
        sc.Add(new Student("Kotlin", 120, 4));
        sc.Add(new Student("Utopia", 400, 6));
        sc.Add(new Student("Marina", 3, 11));
        sc.PrintPassed();

        System.out.println();
        sc.PrintPassed() ;

        sc.SortClass();
        System.out.println();
        sc.PrintPassed() ;

        Consultant cAge= new ConsultantOrderByAge();
        cAge.ExecuteOrder66(sc);
        sc.PrintClass();
    }
}
