import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static LinkedList<Oglas> oglasi;

    public static void main(String[] args) {
        oglasi = generisiOglase();

        zadatak1();
        zadatak2();
        zadatak3();
        zadatak4();
        zadatak5();
        zadatak6();
        zadatak7();
        zadatak8();
    }

     static LinkedList<Oglas> generisiOglase() {
        LinkedList<Oglas> oglasi = new LinkedList<>();
        KategorijaPosla[] kategorije = KategorijaPosla.values();

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 12; j++) {
                oglasi.add(new Oglas((i * 12) + j, kategorije[i]));
            }
        }
        return oglasi;
    }
    public static void zadatak1() {
        System.out.println("Ukupan broj objavljenih oglasa u jednom danu (za svaki datum pojedinačno)");
        oglasi.stream()
                .collect(Collectors.groupingBy(oglas -> oglas.datum))
                .forEach((m, v) -> System.out.printf("%s: %d%n", m.toString(), v.size()));
    }

    public static void zadatak2() {
        KategorijaPosla zadataKategorija = KategorijaPosla.IT;
        System.out.printf("Prosjek plate u kategoriji %s je %.2f%n", zadataKategorija,
                oglasi.stream()
                        .filter(oglas -> oglas.kategorija == zadataKategorija)
                        .mapToDouble(oglas -> oglas.plata)
                        .average()
                        .getAsDouble());
    }
    public static void zadatak3() {
        System.out.println("\n\nGrad u kom se nudi najviše poslova je " +
                oglasi.stream()
                        .collect(Collectors.groupingBy(oglas -> oglas.grad))
                        .entrySet()
                        .stream()
                        .max((entry1, entry2) -> entry1.getValue().size() - entry2.getValue().size())
                        .get()
                        .getKey());
    }
    public static void zadatak4() {
        System.out.println("\n\nPrikaz svih oglasa grupisanih po godinama (godinu odrediti iz datuma)");
        oglasi.stream()
                .collect(Collectors.groupingBy(oglas -> oglas.datum.getYear()))
                .forEach((k, v) -> {
                    System.out.println(k + ":");
                   v.forEach(System.out::println);
                });

        /*Map<Integer, List<Oglas>> mapa = oglasi.stream()
                .collect(Collectors.groupingBy(oglas -> oglas.datum.getYear()));
        mapa.keySet()
                .stream()
                .forEach(godina -> {
                    System.out.println("Godina " + godina + ":");
                    mapa.get(godina).forEach(System.out::println);
                });*/
    }
    public static void zadatak5() {
        System.out.println("\n\nPrikaz svih oglasa sortiranih po vremenu trajanja u opadajućem redoslijedu\n");
        oglasi.stream()
                .sorted((o1, o2) -> o2.trajanje - o1.trajanje)
                .forEach(System.out::println);
    }
    public static void zadatak6() {
        System.out.println("\n\nPrikaz najbolje plaćenog posla za svaku kategoriju");
        /*oglasi.stream()
					.collect(Collectors.groupingBy(oglas -> oglas.kategorija))
					.entrySet()
					.stream()
					.forEach(e -> {
						System.out.printf("%s: %s", e.getKey(),
							e.getValue().stream()
											.max((o1, o2) -> (int)(o1.plata - o2.plata))
											.get());
					});*/

        oglasi.stream()
                .collect(Collectors.groupingBy(oglas -> oglas.kategorija))
                .forEach((m,v) -> System.out.printf("%s: %s%n", m,
                        v.stream()
                        .max((o1, o2) -> (int) (o1.plata - o2.plata))
                        .get()));
    }
    public static void zadatak7() {
        double prosjekDana = oglasi.stream()
                .mapToDouble(oglas -> oglas.plata)
                .average()
                .getAsDouble();
        System.out.printf("%n%nProsječan broj godina radnog iskustva ukupno je %.2f%n", prosjekDana / 365.00);
    }
    public static void zadatak8() {
        oglasi.stream()
                .collect(Collectors.groupingBy(oglas -> oglas.kategorija))
                .forEach((k, v) -> {
                    int ukupnoPoslova = v.size();
                    long ukupnoRemotePoslova = v.stream()
                            .filter(oglas -> oglas.nacinRada == NacinRada.REMOTE)
                            .count();
                    double procenat = (double)ukupnoRemotePoslova / ukupnoPoslova * 100;
                    System.out.printf("%nProcenat remote poslova u %s je %.2f%%", k, procenat);
                });
    }
}
