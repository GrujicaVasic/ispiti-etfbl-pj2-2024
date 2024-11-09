package projekat;

import clanovi.Developer;
import clanovi.Tim;

import java.util.LinkedList;
import java.util.concurrent.PriorityBlockingQueue;

public class Sprint extends Thread {
    public PriorityBlockingQueue<Zadatak> backlog;
    public Tim tim = new Tim();
    public boolean jeKraj = false;
    public int iteracija = 1;
    public LinkedList<Sprint> sprintovi = new LinkedList<>();
    public long pocetak, kraj, trajanje;

    public Sprint() {
        backlog = new PriorityBlockingQueue<>(10, (z1, z2) -> z1.prioritet - z2.prioritet);
    }
    public Sprint(long trajanje) { this.trajanje = trajanje;}

    @Override
    public void run() {
        tim.setujSprint(this);
        tim.pokreniNiti();
        while(iteracija <= 3) {
            System.out.printf("%n%nSPRINT %d %n%n", iteracija);
            pocetak = System.currentTimeMillis();
            backlog = new PriorityBlockingQueue<>(10, (z1, z2) -> z2.prioritet - z1.prioritet);
            try {
                sleep(700); //nisam za ovo, ali ne da mi se koristit' drugi (normalan) mehanizam
                pocetakSprinta();
                while(tim.sm.jePocetakSprinta) {
                    sleep(400);
                }
                obavjestiDevelopere();
                while(!backlog.isEmpty()) {
                    sleep(350);
                }
                synchronized (tim.sm) {
                    tim.sm.notify();
                }
                synchronized (this) {
                    wait();
                }
            } catch(InterruptedException e) { }
            if(iteracija == 3)  {
                jeKraj = true;
                krajSimulacije();
            }
            iteracija++;
            kraj = System.currentTimeMillis();
            sprintovi.add(new Sprint(kraj - pocetak));
        }
        System.out.println("\n\n");
        for(int i = 0; i < 3; i++)
            System.out.printf("Sprint %d, trajanje %d [ms]%n", (i + 1), sprintovi.get(i).trajanje);
        System.out.println("\n\n");
        for(Developer d : tim.developeri)
            System.out.printf("%s, %s uradio %d zadataka %n", d.ime, d.prezime, d.uradjeniZadaci.size());
    }
    public void dodajZadatke(PriorityBlockingQueue<Zadatak> zadaci) {
        zadaci.forEach(zadatak -> backlog.add(zadatak));
    }
    public void pocetakSprinta() {
        synchronized (tim.sm) {
            tim.sm.jePocetakSprinta = true;
            tim.sm.notify();
        }
        synchronized (tim.po) {
            tim.po.notify();
        }
    }
    public synchronized void obavjestiDevelopere() {
        notifyAll();
    }
    public void krajSimulacije() {
        obavjestiDevelopere();
        synchronized (tim.sm) {
            tim.sm.notify();
        }
        synchronized (tim.po) {
            tim.po.notify();
        }
    }
}
