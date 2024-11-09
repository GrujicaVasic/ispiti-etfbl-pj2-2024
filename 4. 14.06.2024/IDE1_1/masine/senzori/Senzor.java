package masine.senzori;

import masine.Masina;
import masine.RadniStatus;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public abstract class Senzor extends Thread {
    public Masina masina;
    public String naziv;
    public double mjerenje;
    public boolean jeKraj = false;
    protected final double gornjaGranicaMjerenja = 50.00;
    protected Random rand = new Random();
    protected String logFajl = "logs" + File.separator;


    public Senzor(String naziv, Masina masina) {
        this.naziv = naziv;
        this.masina = masina;
    }
    public abstract String ispis();
    protected abstract void radi();

    @Override
    public void run() {
        while(!jeKraj) {
            try {
                if(interrupted())
                    throw new RuntimeException();
                radi();
                sleep(500);
            } catch(InterruptedException e) {
                while (RadniStatus.UGASENA == masina.status) {
                    synchronized (masina) {
                        try {
                            masina.wait();
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                if(masina.jeKraj)
                    jeKraj = true;
            }
        }
    }
    protected void upisULogFajl(String upis) {
        try {
            FileWriter fw = new FileWriter(logFajl, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(upis);
            pw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
