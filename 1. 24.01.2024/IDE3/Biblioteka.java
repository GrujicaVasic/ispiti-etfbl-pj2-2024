import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.LinkedList;

public class Biblioteka {
    LinkedList<Sekcija> sekcije;
    public Biblioteka() {
        sekcije = new LinkedList<>();
        sekcije.add(new Sekcija());
        sekcije.add(new Sekcija());
        sekcije.add(new Sekcija());
    }

    public static void main(String[] args) {
        Biblioteka b = new Biblioteka();
        System.out.println("1.");
        b.metoda1(b.sekcije);
        System.out.println("\n\n2.");
        b.metoda2(b.sekcije);
        System.out.print("\n\n3. ");
        b.metoda3(b.sekcije);
        System.out.println("\n\n");
        b.metoda4(b.sekcije);
    }

    private void metoda1(LinkedList<Sekcija> bib) {
        System.out.println("Ukupan broj knjiga svake sekcije");
        bib.stream()
                .forEach(sekcija -> System.out.printf("Sekcija %d, broj knjiga: %d%n", sekcija.id,
                        sekcija.police
                                .stream()
                                .mapToInt(polica -> polica.knjige.size())
                                .sum()));

        System.out.println("\nUkupan broj knjiga za sve sekcije");
        int suma = bib.stream()
                .mapToInt(sekcija -> sekcija.police.stream()
                        .mapToInt(polica -> polica.knjige.size())
                        .sum())
                .sum();
        System.out.println("Ukupno: " + suma);
    }

    private void metoda2(LinkedList<Sekcija> bib) {
        /*Files.lines(new File("KnjigeSpisak.csv").toPath())
                .map(line -> line.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .count()
        bib.stream()
                .map(sekcija -> sekcija.police.stream()
                        .map(polica -> polica.knjige.stream()
                                .flatMap(knjiga -> knjiga.ime + " " + knjiga.prezime)
                        )
                ).distinct().flatMap();
        LinkedList<Polica> p = bib.getFirst().police;
        bib.stream()
                        .flatMap(sekcija -> sekcija.police.stream())
                                .forEach(System.out::println);*/
        bib.stream()
                .flatMap(sekcija -> sekcija.police.stream()
                        .flatMap(polica -> polica.knjige.stream()
                                .map(knjiga -> knjiga.ime + " " + knjiga.prezime)))
                .distinct().forEach(System.out::println);
    }

    private void metoda3(LinkedList<Sekcija> bib) {
        System.out.println("Podaci o knjigama iz svake sekcije za policu koja ima najveći broj knjiga");
        bib.stream()
                .forEach(sekcija -> {
                    System.out.println("Sekcija " + sekcija.id);
                    sekcija.police
                        .stream()
                        .max((p1, p2) -> p1.knjige.size() - p2.knjige.size())
                        .get()
                        .knjige.forEach(System.out::println);
                });
    }
    private void metoda4(LinkedList<Sekcija> bib) {
        System.out.println("4. Sortirati i ispisati sekcije sa pripadajućim policama, knjigama i naslovima po naslovu\n" +
                "knjige u određenoj sekciji opadajuće");
        bib.stream()
                .flatMap(sekcija -> sekcija.police
                        .stream()
                        .flatMap(polica -> polica.knjige
                                .stream()
                                .sorted((k1, k2) -> k1.naslov.compareTo(k2.naslov))))
                .forEach(knjiga -> System.out.println(knjiga.naslov));
    }
}
