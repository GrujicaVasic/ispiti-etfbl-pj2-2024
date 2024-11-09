package vozila;

import putnici.PoolPutnikaThread;
import vozila.izuzeci.ManjakPutnikaException;
import vozila.izuzeci.NedovoljanKapacitetException;

import java.util.LinkedList;

public class SaVagonima extends Tramvaj {
    protected String fajl = "bez_baterije.txt";
    public SaVagonima(PoolPutnikaThread pool, int id) {
        super(pool, id);
    }
    @Override
    public String ulazIspis() {
        return "U tramvaj sa vagonima je uslo putnika: ";
    }
    @Override
    public String izlazIspis() {
        return "Iz tramvaja sa vagonima je izaslo putnika: ";
    }
    @Override
    protected synchronized void ulazePutnici() throws InterruptedException {
        int brojPutnika = rand.nextInt(5) + 1;
        pool.dodajPutnike = true;
        pool.indeksVozila = id;
        pool.brojPutnika = brojPutnika;
        pool.interrupt();
        wait();
    }
    @Override
    protected synchronized void izlazePutnici() throws InterruptedException {
        int brojPutnika = rand.nextInt(5) + 1;
        pool.dodajPutnike = false;
        pool.indeksVozila = id;
        pool.brojPutnika = brojPutnika;
        pool.interrupt();
        wait();
    }
    @Override
    protected LinkedList<String> ispis() {
        LinkedList<String> ispis = new LinkedList<>();
        ispis.add(fajl);
        ispis.add("###Tramvaj sa vagonima:");
        putnici.forEach(putnik -> ispis.add(putnik.toString()));
        return ispis;
    }
}
