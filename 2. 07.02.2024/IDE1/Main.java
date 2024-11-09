import komponente.Baterija;
import komponente.Motor;
import komponente.Senzor;
import komponente.VrstaSenzora;
import roboti.Cistacki;
import roboti.Istrazivacki;
import roboti.Montazni;
import roboti.Robot;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Main {
    static String[] prozvodjaci = new String[] {"Acer", "Sony", "Apple", "CAT"};
    static Random rand = new Random();

    public static void main(String[] args) {
        try {
            Files.deleteIfExists(Path.of("kvarovi.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Robot[] roboti = generisiRobote();
        for(Robot r : roboti)
            r.start();

        Object pauza = new Object();

        try {
            List<String> lines;
            List<String> oldLines = null;
            while(true) {
                lines = Files.readAllLines(Path.of("alarm.txt"));
                sleep(1500);
                if(!lines.isEmpty() && !oldLines.equals(lines)) {
                    String komanda = lines.getFirst();
                    if ("0".contentEquals(komanda)) {
                        nastavi(roboti, pauza);
                        System.out.println("\nNASTAVAK\n");
                    }
                    else if ("1".contentEquals(komanda)) {
                        System.out.println("\nPAUZA\n");
                        pauziraj(roboti, pauza);
                    }
                }
                oldLines = lines;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        catch(IOException e) {
            System.out.println("Problem sa alarm.txt");
        }
    }
    private static void pauziraj(Robot[] roboti, Object pauza) {
        for(Robot r : roboti) {
            r.pauza = pauza;
            r.jePauza = true;
            r.interrupt();
        }
    }
    private static void nastavi(Robot[] roboti, Object pauza) {
        for(Robot r : roboti)
            r.jePauza = false;
        synchronized (pauza) {
            pauza.notifyAll();
        }
    }

    private static Robot[] generisiRobote() {
        Robot[] roboti = new Robot[30];

        for(int i = 0; i < 30; i++) {
            Motor motor = new Motor(100 + i, getProzivodjac(), getGodinaProizvodnje(),
                    rand.nextInt(100));
            Baterija baterija = new Baterija(100 + 1 + i, getProzivodjac(), getGodinaProizvodnje(),
                    rand.nextInt(100));
            Senzor senzor;
            if(i % 2 == 0)
                senzor = new Senzor(100 + 2 + i, getProzivodjac(), getGodinaProizvodnje(),
                        VrstaSenzora.OPTICKI, rand.nextInt(100));
            else
                senzor = new Senzor(100 + 2 + i, getProzivodjac(), getGodinaProizvodnje(),
                        VrstaSenzora.ULTRAZVUCNI, rand.nextInt(100));
            Robot robot;

            if(i < 10) {
                robot = new Montazni(i + 1);
            }
            else if(i < 20) {
                robot = new Cistacki(i + 1);
            }
            else {
                robot = new Istrazivacki(i + 1);
            }
            roboti[i] = robot;
            robot.komponente.add(motor);
            robot.komponente.add(baterija);
            robot.komponente.add(senzor);
        }
        return roboti;
    }
    private static String getProzivodjac() {
        return prozvodjaci[rand.nextInt(prozvodjaci.length)];
    }
    private static int getGodinaProizvodnje() {
        return 2024 - rand.nextInt(24);
    }
}
