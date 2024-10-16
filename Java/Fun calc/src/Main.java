import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        StringFunction stringo = (str, doi) -> str + "!" + doi;
        System.out.println(stringo.run("Incercarea moarte n-are", "dar are rabdare"));
    }
}

interface StringFunction {
    String run(String str, String str2);
}