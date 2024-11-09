package putnici;

import java.util.Random;

public class Putnik {
    public String ime, prezime;
    private String[] imena = new String[] {"Milan", "Mladen", "Dragan", "Jovan", "Stefan", "Simo",
    "Aleksandra", "Predrag", "Nenad", "Rajo", "Milana", "Dragana", "Jovana", "Stefana", "Aleksandra"};
    private String[] prezimena = new String[] {"Tadic", "Radic", "Jovic", "Milic", "Mladenovic", "Jovicic",
    "Stefanovic", "Nenadovic", "Jovanovic"};
    private Random rand = new Random();

    public Putnik() {
        ime = imena[rand.nextInt(imena.length)];
        prezime = prezimena[rand.nextInt(prezimena.length)];
    }
    @Override
    public String toString() {
        return ime + " " + prezime;
    }
}
