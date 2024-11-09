package vozila;

import putnici.PoolPutnikaThread;
import putnici.Putnik;
import vozila.izuzeci.ManjakPutnikaException;
import vozila.izuzeci.NedovoljanKapacitetException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public abstract class Vozilo extends Thread {
    public LinkedList<Putnik> putnici = new LinkedList<>();
    public PoolPutnikaThread pool;
    public int id;
    protected Random rand = new Random();
    protected boolean jeKraj = false;
    private final int trajanjeSimulacije;
    public Vozilo(PoolPutnikaThread pool, int id) {
        this.pool = pool;
        this.id = id;
        int brojPutnika = rand.nextInt(26) + 25;
        for(int i = 0; i < brojPutnika; i++)
            putnici.add(new Putnik());
        trajanjeSimulacije = rand.nextInt(11) + 20;
    }
    public abstract String ulazIspis();
    public abstract String izlazIspis();
    protected abstract LinkedList<String> ispis();

    @Override
    public void run() {
        postavljanjePrekidaSimulacije();
        boolean dodajPutnike;
        while(!jeKraj) {
            dodajPutnike = rand.nextBoolean();
            try {
                if (dodajPutnike)
                    ulazePutnici();
                else
                    izlazePutnici();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                sleep(2000);
            } catch(InterruptedException e) {

            }
        }
        System.out.printf("Vozilo %d zavrsilo voznju%n", id);
        upisUFajl();
    }
    protected synchronized void ulazePutnici() throws InterruptedException {
        int brojPutnika = rand.nextInt(3) + 1;
        pool.dodajPutnike = true;
        pool.indeksVozila = id;
        pool.brojPutnika = brojPutnika;
        pool.interrupt();
        wait();
    }
    protected synchronized void izlazePutnici() throws InterruptedException {
        int brojPutnika = rand.nextInt(3) + 1;
        pool.dodajPutnike = false;
        pool.indeksVozila = id;
        pool.brojPutnika = brojPutnika;
        pool.interrupt();
        wait();
    }
    private void postavljanjePrekidaSimulacije() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                jeKraj = true;
            }
        }, (long)trajanjeSimulacije * 1000L);
    }
    private void upisUFajl() {
        try {
            LinkedList<String> lines = ispis();
            FileWriter fw = new FileWriter(lines.remove(), true);
            PrintWriter pw = new PrintWriter(fw);
            for(String s : lines)
                pw.println(s);
            pw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
