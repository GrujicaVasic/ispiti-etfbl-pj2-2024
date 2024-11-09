package clanovi;

import projekat.Zadatak;

import java.util.LinkedList;
import java.util.Random;

public class Developer extends Clan {
    public LinkedList<Zadatak> uradjeniZadaci = new LinkedList<>();
    private Random rand = new Random();
    public Developer(String ime, String prezime, int godineRada) {
        super(ime, prezime, godineRada);
    }

    @Override
    public void run() {
        while(!sprint.jeKraj) {
            try {
                synchronized (sprint) {
                    sprint.wait();
                }
                while(!sprint.backlog.isEmpty()) {
                    Zadatak z = sprint.backlog.poll();
                    long pocetak = System.currentTimeMillis();
                    uradjeniZadaci.add(z);
                    sleep((rand.nextInt(3) + 1) * 1000L);
                    long kraj = System.currentTimeMillis();
                    z.trajanje = kraj - pocetak;
                    System.out.printf("%s %s zavrsio zadatak %s, prioriteta %d%n", ime, prezime, z.naslov, z.prioritet);
                }
                synchronized (sprint){
                    sprint.notify();
                }
            } catch(InterruptedException e) { }
        }
    }
    @Override
    public String toString() {
        return String.format("%s, %s, %d", ime, prezime, godineRada);
    }
}
