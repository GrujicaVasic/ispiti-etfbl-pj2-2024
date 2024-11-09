package projekat;

import java.util.Random;

public class Zadatak {
    public String naslov, opis;
    public int prioritet;
    public VrsteZadataka vrsta;
    public int brojIteracije;
    public long trajanje;

    public Zadatak(String id, int prioritet) {
        this.prioritet = prioritet;
        naslov = "Naslov " + id;
        opis = "Opis " + id;
        VrsteZadataka[] vrste = VrsteZadataka.values();
        vrsta = vrste[new Random().nextInt(vrste.length)];
    }
    @Override
    public String toString() {
        return String.format("%s, %s, %d, %s, %d [ms]", naslov, opis, prioritet, vrsta.toString(), trajanje);
    }
}
