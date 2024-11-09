import java.util.*;
import java.time.LocalDate;

public class Oglas {
    public String naziv, opis, grad;
    public LocalDate objavljen;
    public int trajanje, iskustvo;
    public double plata;
    public NacinRada nacinRada;
    public KategorijaPosla kategorija;

    private String[] gradovi = {"Brcko", "Beograd", "Banja Luka", "Budimpesta"};
    private LocalDate[] datumi = new LocalDate[] { LocalDate.of(2024, 9, 22), LocalDate.of(2024, 9, 21), LocalDate.of(2024, 9, 17),
            LocalDate.of(2024, 8, 20), LocalDate.of(2024, 8, 13), LocalDate.of(2023, 12, 01)};
    private NacinRada[] nacini = NacinRada.values();
    private Random rand = new Random();


    public Oglas(int brojac, KategorijaPosla kategorija) {
        this.kategorija = kategorija;
        naziv = "Naziv " + brojac;
        opis = "Opis " + brojac;
        grad = gradovi[rand.nextInt(gradovi.length)];
        objavljen = datumi[rand.nextInt(datumi.length)];
        trajanje = rand.nextInt(365) + 30;
        iskustvo = rand.nextInt(20);
        plata = rand.nextDouble(2000) + 900.00;
        nacinRada = nacini[rand.nextInt(nacini.length)];
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %d, %d, %.2f, %s, %s", naziv, opis, grad, objavljen.toString(), trajanje, iskustvo,
                plata, nacinRada.toString(), kategorija.toString());
    }
}