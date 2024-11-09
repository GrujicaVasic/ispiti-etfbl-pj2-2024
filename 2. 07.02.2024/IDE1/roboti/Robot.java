package roboti;

import komponente.Komponenta;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public abstract class Robot extends Thread {
    public List<Komponenta> komponente = new LinkedList<>();
    public int serijskiBroj, snaga, visina;
    public TipRobota tip;
    public boolean autonomija, jeKraj, jeOstecen, jePauza;
    public Object pauza;
    private final Random  rand = new Random();
    private Timer timer = new Timer();
    private long pocetakRada, prekidRada, vrijeme;
    public Robot(int serijskiBroj, TipRobota tip) {
        this.serijskiBroj = serijskiBroj;
        this.tip = tip;
        snaga = rand.nextInt(100);
        visina = rand.nextInt(200);
        autonomija = rand.nextBoolean();
        jeKraj = jeOstecen = jePauza = false;
    }

    public abstract String pocetakAkcije();
    public abstract String krajAkcije();

    @Override
    public void run() {
        ostecenje();
        while(!jeKraj) {
            try {
                if(interrupted())
                    throw new InterruptedException();
                if(jeOstecen)
                    throw new InterruptedException();
                vrijeme = vrijemeAkcije();
                System.out.println(String.format("%.2f[s], %s", (vrijeme / 1000.00), pocetakAkcije()));
                //System.out.println(pocetakAkcije());
                pocetakRada = System.currentTimeMillis();
                sleep(vrijeme);
                System.out.println(krajAkcije());
            } catch (InterruptedException e) {
                if(jePauza) {
                    timer.cancel();
                    prekidRada = System.currentTimeMillis();
                    while(jePauza) {
                        synchronized (pauza) {
                            try {
                                pauza.wait();
                            } catch (InterruptedException ignored) { }
                        }
                    }
                    try {
                        sleep(vrijeme - (prekidRada - pocetakRada));
                    } catch (InterruptedException ex) {
                    }
                    ostecenje();
                    System.out.println(krajAkcije());
                }
                else if(jeOstecen) {
                    timer.cancel();
                    System.err.println(String.format("Robot %d je ostecen, povlaci se iz rada!", serijskiBroj));
                    upisOstecenja();
                    jeKraj = true;
                }
            }
        }
    }
    @Override
    public String toString() {
        return String.format("id=%d, snaga=%d, visina=%d, tip=%s, autonomija=%b%n%s",
                serijskiBroj, snaga, visina, tip.toString(), autonomija, podaciOKomponentama());
    }
    protected String podaciOKomponentama() {
        StringBuilder sb = new StringBuilder("Komponente: \n");
        for(Komponenta k : komponente)
            sb.append(k + "\n");
        return sb.toString();
    }
    private void ostecenje() {  //ako robot bude ostecen, timer treba prekinut, timer.cancel()
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int n = rand.nextInt(100) + 1;
                if(n < 5)
                    jeOstecen = true;
            }
        }, 0, 1_000L);
    }
    private long vrijemeAkcije() {
        return (rand.nextInt(21) + 10) * 1000L;
    }
    private void upisOstecenja() {
        try {
            FileWriter fw = new FileWriter("kvarovi.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(this);
            pw.close();
        } catch (IOException e) {
            System.err.println("Greska sa fajlom ostecenja!");
        }
    }
}
