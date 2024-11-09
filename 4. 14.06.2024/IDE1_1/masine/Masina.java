package masine;

import masine.senzori.Senzor;

import java.util.LinkedList;

public class Masina extends Thread {
    public LinkedList<Senzor> senzori = new LinkedList<>();
    public int id;
    public String model;
    public RadniStatus status;
    public boolean jeKraj = false;
    public Object notifikacija;
    public Masina(int id, String model, Object notifikacija) {
        this.id = id;
        this.model = model;
        this.notifikacija = notifikacija;
        status = RadniStatus.UPALJENA;
    }
    @Override
    public void run() {
        senzori.forEach(Thread::start);
        while(!jeKraj) {
            try {
                if(interrupted())
                    throw new InterruptedException();
                ispisMjerenja();
                sleep(1_000);
            } catch(InterruptedException e) {
                while(RadniStatus.UGASENA == status) {
                    try {
                        synchronized (notifikacija) {
                            notifikacija.wait();
                        }
                    } catch(InterruptedException ie) { }
                }
                synchronized (this) {   //this - zato sto senzori kad su u stanju cekanja, cekaju na objekat masine ciji su element
                    notifyAll();    //obavjestava sve senzore masine
                }
            }
        }
        senzori.forEach(Thread::interrupt);
    }
    public synchronized void upali() {
        System.out.printf("Masina %d upaljena%n", id);
        status = RadniStatus.UPALJENA;
    }
    public void ugasi() {
        status = RadniStatus.UGASENA;
        senzori.forEach(Thread::interrupt);
        System.out.printf("Masina %d je ugasena%n", id);
    }

    private synchronized void ispisMjerenja() { //synchronized da se ispis ne bi mijesao na konzoli
        System.out.printf("%nMasina %d, mjerenja senzora:%n", id);
        for(Senzor s : senzori)
            System.out.printf("   %s%n", s.ispis());
        System.out.println();
    }
}
