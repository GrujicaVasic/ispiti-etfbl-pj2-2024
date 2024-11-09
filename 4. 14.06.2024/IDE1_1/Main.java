import masine.Masina;
import masine.senzori.PritisakSenzor;
import masine.senzori.TemperaturaSenzor;
import masine.senzori.VibracijaSenzor;
import masine.senzori.VlagaSenzor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Object notif = new Object();
        Masina varenje = new Masina(1, "Makita", notif);
        Masina sjecenje = new Masina(2, "Hilti", notif);

        PritisakSenzor psv = new PritisakSenzor(varenje);
        TemperaturaSenzor tsv = new TemperaturaSenzor(varenje);
        varenje.senzori.add(psv);
        varenje.senzori.add(tsv);

        TemperaturaSenzor tss = new TemperaturaSenzor(sjecenje);
        VibracijaSenzor vss = new VibracijaSenzor(sjecenje);
        VlagaSenzor vlss = new VlagaSenzor(sjecenje);
        sjecenje.senzori.add(tss);
        sjecenje.senzori.add(vss);
        sjecenje.senzori.add(vlss);

        varenje.start();
        sjecenje.start();

        String unos = "", prethodniUnos = "";
        Scanner scan = new Scanner(System.in);
        while(!"2".contentEquals(unos)) {
            unos = scan.nextLine();
            if(!unos.contentEquals(prethodniUnos)) {
                if ("1".contentEquals(unos)) {
                    varenje.upali();
                    sjecenje.upali();
                    synchronized (notif) {
                        notif.notifyAll();
                    }
                } else if ("0".contentEquals(unos)) {
                    varenje.ugasi();
                    sjecenje.ugasi();
                    varenje.interrupt();
                    sjecenje.interrupt();
                }
            }
            prethodniUnos = unos;
        }
        //na osnovu teksta zadatka, mislim da je gasenje simulacije nepotrebno - nije nigdje navedeno
        varenje.jeKraj = true;
        sjecenje.jeKraj = true;
        varenje.interrupt();
        sjecenje.interrupt();
        //ima neki bug, ne moze se zavrsit' aplikacija ako se proba okoncat' dok su masine ugasene
        //vjerovatno senzori ostanu u stanju cekanja, a cekaju masine koje su ugasene...
    }
}
