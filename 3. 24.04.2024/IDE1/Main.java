import simulacija.Simulacija;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Unesi broj knjiga: ");
        int brojKnjiga = 0;
        while(brojKnjiga < 30) {
            String unos = scan.nextLine();
            try {
                brojKnjiga = Integer.parseInt(unos);
                if(brojKnjiga < 30)
                    throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.err.println("Unesena vrijednost mora biti broj manji od 30!");
            }
        }
        Simulacija s = new Simulacija(brojKnjiga);
        s.start();
    }
}
