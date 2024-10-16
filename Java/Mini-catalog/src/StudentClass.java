//package java.lang ;

import java.lang.*;

class StudentClass {
    public Student[] v;
    private int n;
    private int N = 20;

    public StudentClass() {
        n = 0;
        v = new Student[N];
    }

    public StudentClass(int nrOfStudents) {
        N = nrOfStudents;
        v = new Student[nrOfStudents];
    }

    public void Add(Student s) {
        if (n < N) {
            v[n] = s;
            n++;
        }
    }

    public void PrintPassed() {
        System.out.print("Class:  ");
        for (int i = 0; i < n; i++) {
            if (v[i].grade >= 5) {
                System.out.print("Student " + i + ": Nume: " + v[i].name + "\n\t\t\t\t  Varsta: " + v[i].age + "\n\t\t\t\t  Nota: " + v[i].grade + "\n\t\t");
            }
        }
        System.out.println();
    }

    public void PrintClass() {
        System.out.print("Class:  ");
        for (int i = 0; i < n; i++) {
            System.out.print("Student " + i + ": Nume: " + v[i].name + "\n\t\t\t\t  Varsta: " + v[i].age + "\n\t\t\t\t  Nota: " + v[i].grade + "\n\t\t");
        }
        System.out.println();
    }

    public void SortClass() {
        int i, j;
        for (i = 0; i < n - 1; i++) {
            for (j = 0; j < n - i - 1; j++) {
                if (v[j].compareTo(v[j + 1]) < 0)//v[j].grade < v[j+1].grade
                {
                    Student temp = v[j];
                    v[j] = v[j + 1];
                    v[j + 1] = temp;
                }
            }
        }
    }

    public int Getn() {
        return n;
    }
}