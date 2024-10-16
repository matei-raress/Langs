import java.util.*;

class Complex {
    public int real;
    public int imag;

    Complex() {
        real = 0;
        imag = 0;
    }

    Complex(int a, int b) {
        real = a;
        imag = b;
    }

    Complex add(Complex a) {
        Complex temp = new Complex();
        temp.real = this.real + a.real;
        temp.imag = this.imag + a.imag;
        return temp;
    }

    Complex multiply(Complex a) {
        Complex temp = new Complex();
        temp.real = this.real * a.real - this.imag * a.imag;
        temp.imag = this.imag * a.real + this.real * a.imag;
        return temp;
    }

    Complex power(int nr) {
        for (int i = 0; i < nr - 1; i++) {
            this.imag = this.imag * this.imag;
            this.real = this.real * this.real;
        }
        return this;
    }

    boolean Equals(Complex a) {
        if (this.real == a.real && this.imag == a.imag) return true;

        return false;
    }

    String ToString() {
        String st1 = Integer.toString(this.real);
        String st2 = Integer.toString(this.imag);
        return st1 + st2;
    }
}

public class Main {

    public static void main(String[] args) {
        Complex test = new Complex(2, 3);
        Complex compl = new Complex(3, 2);
        Complex result = new Complex();

        result = test.add(compl);

        System.out.println("Adunare :" + result.real + "+" + result.imag + "i");

        result = test.multiply(compl);
        System.out.println("Inmultire : " + result.real + "+" + result.imag + "i");

        test.power(2);
        System.out.println("Nr complex la putere 2: " + test.real + " +" + test.imag + "i");

        System.out.println("String : " + test.ToString());


    }
}
