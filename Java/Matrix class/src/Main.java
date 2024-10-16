import java.util.Arrays;

class Matrix {

    static double[][] adunare(double[][] a, double[][] b) {
        double[][] suma = new double[a.length][a.length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                suma[i][j] = a[i][j] + b[i][j];
            }
        }
        return suma;
    }

    static double[][] inmultire(double[][] a, double[][] b) {
        double[][] produs = new double[a.length][a.length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                produs[i][j] = 0;
                for (int k = 0; k < a.length; k++)
                    produs[i][j] += a[i][k] * b[k][j];
            }
        }
        return produs;
    }

    @override
    String toString(double[][] a) {
        StringBuilder rez = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++)
                rez.append(a[i][j]);
        }
        return rez.toString();
    }

    static double[][] Putere(double[][] a, int p) {
        double[][] put = new double[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        for (int i = 0; i < p; i++) {
            put = inmultire(put, a);
        }
        return put;
    }
}

public class Main {

    public static void main(String[] args) {
        double[][] a, b;
        a = new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        b = new double[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Adunarea este ");
        double[][] c = Matrix.adunare(a, b);
        for (double[] ints : c) {
            for (double anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }

        System.out.println("Inmultire:");
        double[][] d = Matrix.inmultire(a, b);
        for (double[] ints : d) {
            for (double anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println();

        double[][] g = Matrix.Putere(a, 2);
        System.out.println("Putere cu 2");
        for (double[] ints : g) {
            for (double anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println();
        double[][] h = Matrix.Putere(a, 3);
        for (double[] ints : h) {
            for (double anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println();
        String m = Matrix.ToString(a);
        System.out.print(m);
    }
}


