package clanovi;

import projekat.Sprint;

import java.util.LinkedList;

public class Tim {
    public ProductOwner po = new ProductOwner("Grujica", "Vasic", 14, "Kralj coek");
    public ScrumMaster sm = new ScrumMaster("Nazario", "Lima", 9);
    public LinkedList<Developer> developeri = new LinkedList<>();
    public Tim() {
        developeri.add(new Developer("Gianluigi", "Buffon", 5));
        developeri.add(new Developer("Scottie", "Pippen", 4));
        developeri.add(new Developer("Rudd", "Gulit", 4));
    }
    public void pokreniNiti() {
        po.start();
        sm.start();
        developeri.forEach(Thread::start);
    }
    public void setujSprint(Sprint spr) {
        po.sprint = spr;
        sm.sprint = spr;
        developeri.forEach(d -> d.sprint = spr);
    }
}
