package putnici;

import vozila.Vozilo;
import vozila.izuzeci.ManjakPutnikaException;
import vozila.izuzeci.NedovoljanKapacitetException;

import java.util.LinkedList;
import java.util.Random;

public class PoolPutnikaThread extends Thread {
    public Vozilo[] vozila;
    public boolean dodajPutnike = true; //vozila ce setovat' ovaj boolean pa na osnovu njega znam treba li dodat' ili izbacit' putnike
    public int indeksVozila;    //znam kojem vozilu dodajem (pozicija u listi svih vozila)
    public int brojPutnika;
    private LinkedList<Putnik> putniciPool = new LinkedList<>();
    private Random rand = new Random();

    public PoolPutnikaThread() {
        int granica = rand.nextInt(30) + 100;
        for(int i = 0; i < granica; i++)
            putniciPool.add(new Putnik());
    }

    @Override
    public void run() {
        while(true) {
            try {
                synchronized (this) {
                    wait();
                }
            } catch (InterruptedException e) {
                try {
                    if (dodajPutnike)
                        dodajUVozilo(vozila[indeksVozila], brojPutnika);
                    else
                        povuciIzVozila(vozila[indeksVozila], brojPutnika);
                } catch(NedovoljanKapacitetException | ManjakPutnikaException e1) {
                    System.err.println(e1.getMessage());
                }
            } finally {
                synchronized (vozila[indeksVozila]) {
                    vozila[indeksVozila].notify();
                }
            }
        }
    }
    public void dodajUVozilo(Vozilo vozilo, int broj) throws NedovoljanKapacitetException {
        int ukupnoPutnika = vozilo.putnici.size() + broj;
        if(ukupnoPutnika > 50)
            throw new NedovoljanKapacitetException(vozilo.getClass().getSimpleName());
        while(putniciPool.size() - broj < 100)
            generisiJosPutnika();
        for(int i = 0; i < broj; i++)
            vozilo.putnici.add(putniciPool.remove());
        System.out.println(vozilo.ulazIspis() + brojPutnika);
    }
    public void povuciIzVozila(Vozilo vozilo, int broj) throws ManjakPutnikaException {
        int ukupnoPutnika = vozilo.putnici.size() - broj;
        if(ukupnoPutnika < 25)
            throw new ManjakPutnikaException(vozilo.getClass().getSimpleName());
        for(int i = 0; i < ukupnoPutnika; i++)
            putniciPool.add(vozilo.putnici.remove());
        System.out.println(vozilo.izlazIspis() + brojPutnika);
    }
    private void generisiJosPutnika() {
        int dodati = rand.nextInt(5);
        for(int i = 0; i < dodati; i++)
            putniciPool.add(new Putnik());
    }
}
