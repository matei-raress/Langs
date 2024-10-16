//import libraria principala polyglot din graalvm

import org.graalvm.polyglot.*;

class Polyglot {

    private static int input() {
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value valoarePol = polyglot.eval("python", "int(input());");
        int result = valoarePol.asInt();
        polyglot.close();
        return result;
    }

    private static double pBinom(int x, int size, double prob) {    // P(X<=x)
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value valoarePol = polyglot.eval("R", "pbinom(" + x + "," + size + "," + prob + ");");
        Double result = valoarePol.asDouble();
        polyglot.close();
        return result;
    }

    private static double DBinom(int x, int size, double prob) {    // P(X<=x)
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        Value valoarePol = polyglot.eval("R", "dbinom(" + x + "," + size + "," + prob + ");");
        Double result = valoarePol.asDouble();
        polyglot.close();
        return result;
    }
    //Folosim o variabila generica care va captura rezultatul excutiei functiei PYTHON, sum()
    //Avem voie sa inlocuim anumite elemente din scriptul pe care il construim spre evaluare, aici token provine din JAVA, dar va fi interpretat de PYTHON
    //Value result = polyglot.eval("python", "sum(ord(ch) for ch in '" + token + "')");

    //Functia MAIN
    public static void main(String[] args) {
        System.out.println("Nr de aruncari");
        int nr = input();
        System.out.println("x=");
        int x = input();

        double p = 0.5;
        double result = pBinom(x, nr, p);
        double resultd = DBinom(x, nr, p);
        System.out.println("P(X<=" + x + ") =" + result);
        System.out.print("P(X=" + x + ") =" + resultd);
    }
}

