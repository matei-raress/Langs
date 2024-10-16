//package java.lang ;
import java.lang.*;

class Student implements Comparable<Student>
{
    protected String name;
    protected int age;
    protected int grade;

    public Student() {
        name = "";
        age = 0;
        grade = 0;
    }
    public Student(String nume, int varsta ,int nota) {
        name = nume;
        age = varsta;
        grade = nota;
    }
    public Student (Student s) {
        this.age = s.age;
        this.name = s.name;
        this.grade = s.grade;
    }

    @Override public String toString()
    {
        return super.toString();
    }

    @Override public Student clone(){
        return new Student(this);
    }

    // if they have the same age and name, they are compared by grade
    @Override public int compareTo(Student elem){
        int cmp;
        cmp = this.name.compareTo(elem.name);
        if(cmp!=0)
        {
            return cmp;
        }

        cmp = this.age - elem.age;
        if(cmp!=0)
        {
            return cmp;
        }
        return this.grade - elem.grade;
    }

    public int compareName(Student a){
        return this.name.compareTo(a.name);
    }
    public int compareAge(Student a){
        return this.age - a.age ;
    }
    public int compareGrade(Student a){
        return this.grade - a.grade ;
    }

    public  String GetName()
    {
        return this.name;
    }
    public int GetAge()
    {
        return this.age;
    }
    public int GetGrade(){
        return this.grade ;
    }

}