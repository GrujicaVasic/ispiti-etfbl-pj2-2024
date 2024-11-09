import simulacija.Simulacija;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Simulacija sim = new Simulacija();
        sim.start();

        String kraj = "";
        Scanner scan = new Scanner(System.in);
        while(!"kraj".contentEquals(kraj)) {
            kraj = scan.nextLine();
        }
        sim.jeKraj = true;
        synchronized (sim) {
            sim.notify();
        }
        try {
            sim.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\nSimulacija zavrsena");
        System.exit(0);
    }
}
