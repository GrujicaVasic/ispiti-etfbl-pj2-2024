import java.time.LocalDate;
import java.util.Random;

public class Oglas {
    public String naziv, opis, grad;
    public LocalDate datum;
    public int trajanje, iskustvo;
    public double plata;
    public NacinRada nacinRada;
    public KategorijaPosla kategorija;

    private LocalDate[] datumi = new LocalDate[] { LocalDate.of(2024, 9, 17),
            LocalDate.of(2024, 9, 15), LocalDate.of(2024, 9, 13),
            LocalDate.of(2024, 9, 12), LocalDate.of(2024, 9, 11),
            LocalDate.of(2024, 9, 10), LocalDate.of(2024, 9, 8),
            LocalDate.of(2023, 9, 15), LocalDate.of(2023, 9, 13),
            LocalDate.of(2023, 9, 12), LocalDate.of(2022, 9, 11),
            LocalDate.of(2022, 9, 10), LocalDate.of(2021, 9, 8)};
    private String[] gradovi = new String[] {"Beograd", "Brcko", "Banja Luka", "Bec", "Budimpesta", "Boston"};
    private Random rand = new Random();

    public Oglas(int broj, KategorijaPosla kp) {
        naziv = "Naziv " + broj;
        opis = "Opis za posao " + broj;
        plata = rand.nextDouble(2500) + 1_000;
        grad = gradovi[rand.nextInt(gradovi.length)];
        datum = datumi[rand.nextInt(datumi.length)];
        trajanje = rand.nextInt(41) + 10;
        iskustvo = rand.nextInt(30);
        nacinRada = rand.nextBoolean() ? NacinRada.REMOTE : NacinRada.U_SJEDISTU;
        kategorija = kp;
    }
    @Override
    public String toString() {
        return String.format("%s, %s, %.2f, %s, %s, %d, %d, %s, %s", naziv, opis, plata, grad,
                datum.toString(), trajanje, iskustvo, nacinRada, kategorija);
    }
}