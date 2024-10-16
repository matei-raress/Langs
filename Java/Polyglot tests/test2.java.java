//import libraria principala polyglot din graalvm
import org.graalvm.polyglot.*;

//Clasa principala - aplicatie JAVA
class Polyglot {
    //Metoda privata pentru conversie low-case -> up-case folosind functia toupper() din R
    private static String RToUpper(String token){
        //Construim un context care ne permite sa folosim elemente din R
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        //Folosim o variabila generica care va captura rezultatul excutiei funcitiei R, toupper(String)
        //Pentru aexecuta instructiunea I din limbajul X, folosim functia graalvm polyglot.eval("X", "I");
        Value result = polyglot.eval("R", "toupper(\""+token+"\");");
        //Utilizam metoda asString() din variabila incarcata cu output-ul executiei pentru a mapa valoarea generica la un String
        String resultString = result.asString();
        // Inchidem contextul Polyglot
        polyglot.close();
        return resultString;
    }

    //Metoda privata pentru evaluarea unei sume de control simple a literelor unui text ASCII, folosind PYTHON
    private static int SumCRC(String token){
        //Construim un context care ne permite sa folosim elemente din PYTHON
        Context polyglot = Context.newBuilder().allowAllAccess(true).build();
        //Folosim o variabila generica care va captura rezultatul excutiei functiei PYTHON, sum()
        //Avem voie sa inlocuim anumite elemente din scriptul pe care il construim spre evaluare, aici token provine din JAVA, dar va fi interpretat de PYTHON
        Value result = polyglot.eval("python", "sum(ord(ch) for ch in '" + token + "')");
        //Utilizam metoda asInt() din variabila incarcata cu output-ul executiei, pentru a mapa valoarea generica la un Int
        int resultInt = result.asInt();
        //Inchidem contextul Polyglot
        polyglot.close();
        return resultInt;
    }

    // MAIN
    public static void main(String[] args) {
        //Construim un context pentru evaluare elemente JS
        Context polyglot = Context.create();
        //Construim un array de string-uri, folosind cuvinte din pagina web:  https://chrisseaton.com/truffleruby/tenthings/
        Value array = polyglot.eval("js", "[\"If\",\"we\",\"large\",\"fi\",\"there\",\"The\",\"gelar\",\"file\",\"is\",\"150\",\"MB\"];");

        long n=array.getArraySize() ;
        int m=(int)n ;
        int[] arrayCode= new int[m] ;

        //Pentru fiecare cuvant, convertim la upcase folosind R si calculam suma de control folosind PYTHON
        for (int i = 0; i < array.getArraySize();i++){
            String element = array.getArrayElement(i).asString();
            String upper = RToUpper(element);
            int crc = SumCRC(upper);
            arrayCode[i]=crc ;
            System.out.println(upper + " -> " + crc);
        }
        for (int i = 0; i < m ;i++)
        {
            int comp =  arrayCode[i] ;
            if(arrayCode[i] != 0 ) {
                System.out.print(arrayCode[i] + " ");
                for (int j = 0; j < m; j++) {
                    if (comp == arrayCode[j]) {
                        arrayCode[j] = 0;
                        String element = array.getArrayElement(j).asString();
                        System.out.print(" " + element);
                    }
                }
                System.out.println();
            }
        }
        // inchidem contextul Polyglot
        polyglot.close();
    }
}

