package knjige;

import java.time.LocalDate;
import java.util.Random;

public abstract class Knjiga {
    public String naslov, autor;
    public int stranica, godina;
    protected Random rand = new Random();

    public Knjiga(int redniBroj) {
        naslov = "Naslov " + redniBroj;
        autor = "Autor " + redniBroj;
        stranica = rand.nextInt(1000) + 20;
        godina = LocalDate.now().getYear() - rand.nextInt(500);
    }
    //kad sam vec napravio apstraktnu klasu, dodacu i apstraktnu metode - da nasljednice ne implementiraju interfejse OnlineIznajmljivanje i UzivoIznajmljivanje
    public abstract void podigni();

    @Override
    public String toString() {
        return String.format("naslov=%s, autor=%s, stranica=%d, godina=%d", naslov, autor, stranica, godina);
    }
}
