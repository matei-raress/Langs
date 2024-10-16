import java.io.IOException;
import java.io.*;  //libraria intrare/iesire
import java.lang.Math;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        int o1, m1, s1, o2, m2, s2, ora_temp, min_temp, sec_temp, seconds1, seconds2, tmp;

        Scanner input = new Scanner(System.in);

        do {
            System.out.println("Hour of connection =");
            o1 = input.nextInt();
            if (o1 > 24 || o1 < 0) {
                System.out.println("Invalid hour!");
            }
        } while (o1 <= 24 && o1 >= 0);

        do {
            System.out.println("Minute of connection =");
            m1 = input.nextInt();
            if (m1 > 60 || m1 < 0) {
                System.out.println("Invalid minutes!");
            }
        } while (m1 <= 60 && m1 >= 0);

        do {
            System.out.println("Second of connection =");
            s1 = input.nextInt();
            if (s1 > 60 || s1 < 0) {
                System.out.println("Invalid seconds!");
            }
        } while (s1 <= 60 && s1 >= 0);

        do {
            System.out.println("Hour of disconnection =");
            o2 = input.nextInt();
            if (o2 > 24 || o2 < 0) {
                System.out.println("Invalid hour!");
            }
        } while (o2 <= 24 && o2 >= 0);

        do {
            System.out.println("Minute of disconnection =");
            m2 = input.nextInt();
            if (m2 > 60 || m2 < 0) {
                System.out.println("Invalid minutes!");
            }
        } while (m2 <= 60 && m2 >= 0);

        do {
            System.out.println("Second of disconnection =");
            s2 = input.nextInt();
            if (s2 > 60 || s2 < 0) {
                System.out.println("Invalid seconds!");
            }
        } while (s2 <= 60 && s2 >= 0);

        if (s2 == s1 && m2 == m1 && o2 == o1) {
            System.out.printf("The connection lasted 24 hours");
        } else {
            if (o1 <= o2) {
                seconds1 = o1 * 60 * 60 + m1 * 60 + s1;
                seconds2 = o2 * 60 * 60 + m2 * 60 + s2;
                tmp = seconds2 - seconds1;
                o1 = tmp / (60 * 60);
                tmp = tmp % (60 * 60);
                m1 = tmp / 60;
                s1 = tmp % 60;
                System.out.printf("The connection lasted %d hours %d min %d sec", o1, m1, s1);
            } else   //o1>o2
            {
                seconds1 = o1 * 60 * 60 + m1 * 60 + s1;
                seconds2 = o2 * 60 * 60 + m2 * 60 + s2;
                tmp = 24 * 60 * 60 - seconds1 + seconds2;
                o1 = tmp / (60 * 60);
                tmp = tmp % (60 * 60);
                m1 = tmp / 60;
                s1 = tmp % 60;
                System.out.printf("The connection lasted %d hours %d min %d sec", o1, m1, s1);
            }
        }


    }
}
