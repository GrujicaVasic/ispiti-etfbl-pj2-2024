package zaposleni;

import simulacija.Simulacija;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Racunovodja extends Zaposleni {
    protected long trajanje = 10_000L;
    public boolean jeObracun;
    public Racunovodja(String ime, String prezime, int staz, double cijena, Simulacija sim) {
        super(ime, prezime, staz, cijena, sim);
        jeObracun = false;
    }

    @Override
    public void run() {
        System.out.printf("%s %s pocinje%n", ime, prezime);
        String posao = "";
        while(!jeKraj) {
            try {
                if(interrupted())
                    throw new InterruptedException();
                sleep(trajanje);
            } catch(InterruptedException e) {
                if(jeObracun) {
                    jeObracun = false;
                    obracun();
                }
            }
            posao = radi();
            System.out.println(posao);
            zadaci.add(posao);
        }
    }

    @Override
    public String radi() {
        return "Obracun troskova #" + brojac++;
    }

    private void obracun() {
        System.out.printf("\nRacunovodja %s obracunava \n", ime);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s, %s, %.2f%n", sim.rn.ime, sim.rn.prezime, sim.rn.cijena + sim.rn.staz));
        sb.append(String.format("%s, %s, %.2f", sim.rp.ime, sim.rp.prezime, sim.rp.cijena + sim.rp.staz));
        try {
            PrintWriter pw = new PrintWriter("obracun.txt");
            pw.println(sb);
            pw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
