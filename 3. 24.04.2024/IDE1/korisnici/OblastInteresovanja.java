package korisnici;

import knjige.Oblasti;
import knjige.Zanrovi;

import java.util.Random;

public class OblastInteresovanja {
    public Oblasti oblast;
    public Zanrovi zanr;
    public OblastInteresovanja() {
        Oblasti[] oblasti = Oblasti.values();
        Zanrovi[] zanrovi = Zanrovi.values();
        Random rand = new Random();
        oblast = oblasti[rand.nextInt(oblasti.length)];
        zanr = zanrovi[rand.nextInt(zanrovi.length)];
    }
    @Override
    public String toString() {
        return String.format("oblast=%s, zanr=%s", oblast.toString(), zanr.toString());
    }
}
