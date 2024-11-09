package simulacija;

import zaposleni.Racunovodja;
import zaposleni.RadnikNabavke;
import zaposleni.RadnikProdaje;
import zaposleni.Zaposleni;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Simulacija extends Thread {
    public RadnikNabavke rn;
    public RadnikProdaje rp;
    Racunovodja r;
    Timer timer;
    public boolean jeKraj;
    long pocetak, kraj;
    public Simulacija() {
        rn = new RadnikNabavke("Johnny", "Depp", 3, 1200, this);
        rp = new RadnikProdaje("Nazario", "Lima", 5, 1400, this);
        r = new Racunovodja("Milance", "Radosavljevic", 7, 1900, this);
        jeKraj = false;
        timer = new Timer();
    }
    @Override
    public void run() {
        pocetak = System.currentTimeMillis();
        postaviPauze();
        rn.start();
        rp.start();
        r.start();
        while(!jeKraj) {
            try {
                if(rn.obracunaj && rp.obracunaj) {
                    rn.obracunaj = rp.obracunaj = false;
                    r.jeObracun = true;
                    r.interrupt();
                }
                synchronized (this) {
                    wait();
                }
            } catch (InterruptedException e) {

            }
        }
        rn.jeKraj = rp.jeKraj = r.jeKraj = true;
        rn.interrupt();
        rp.interrupt();
        r.interrupt();
        kraj = System.currentTimeMillis();
        ispisZaKraj();
    }
    private void postaviPauze() {
        Zaposleni[] zapolseni = new Zaposleni[] {rp, rn, r};
        Random rand = new Random();
        for(Zaposleni z : zapolseni) {
            if(rand.nextBoolean()) {
                long pocetakPauze = (rand.nextInt(16) + 5) * 1000L;
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        z.jePauza = true;
                        z.interrupt();
                    }
                }, pocetakPauze);
             }
        }
    }
    private void ispisZaKraj() {
        double trajanje = (kraj - pocetak) / 1000.00;
        System.out.printf("%nUkupno trajanje simulacije: %.2f [s]%n%n", trajanje);
        Zaposleni[] zapolseni = new Zaposleni[] {rp, rn, r};
        System.out.println("#Ime, prezime, broj zadataka#");
        for(Zaposleni z : zapolseni)
            System.out.printf("%s, %s, %d%n", z.ime, z.prezime, z.zadaci.size());
    }
}
