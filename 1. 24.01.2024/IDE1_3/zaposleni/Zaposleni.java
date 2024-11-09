package zaposleni;

import simulacija.Simulacija;

import java.util.LinkedList;

public abstract class Zaposleni extends Thread {
    public String ime, prezime;
    public int staz;
    public double cijena;
    public LinkedList<String> zadaci;
    public boolean jeKraj, jePauza, obracunaj;
    protected int brojac;
    protected long trajanje = 3000L;
    protected final Simulacija sim;

    public Zaposleni(String ime, String prezime, int staz, double cijena, Simulacija sim) {
        this.ime = ime;
        this.prezime = prezime;
        this.staz = staz;
        this.cijena = cijena;
        this.sim = sim;
        brojac = 1;
        zadaci = new LinkedList<>();
        jeKraj = false;
        jePauza = false;
        obracunaj = false;
    }

    public abstract String radi();

    @Override
    public void run() {
        System.out.printf("%s %s pocinje%n", ime, prezime);
        String posao = "";
        while(!jeKraj) {
            try {
                if(interrupted())
                    throw new InterruptedException();
                sleep(trajanje);
            if(zadaci.size() == 10) {
                synchronized (sim) {
                    obracunaj = true;
                    sim.notify();
                }
            }
                posao = radi();
                System.out.println(posao);
                zadaci.add(posao);
            } catch(InterruptedException e) {
                if(jePauza) {
                    jePauza = false;
                    try {
                        System.out.printf("%s %s ide na pauzu%n", ime, prezime);
                        sleep(5_000);
                        System.out.printf("%s %s zavrsio sa pauzom%n", ime, prezime);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

        }
    }
}
