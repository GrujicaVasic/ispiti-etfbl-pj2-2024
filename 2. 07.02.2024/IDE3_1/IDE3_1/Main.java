import java.util.*;
import java.util.stream.*;

public class Main {
    private static LinkedList<Oglas> oglasi = new LinkedList<>();

    public static void main(String[] args) {
        generisi();
        zadatak1();
        zadatak2();
        zadatak3();
        zadatak4();
        zadatak5();
        zadatak6();
        zadatak7();
        zadatak8();
    }

    private static  void zadatak1() {
        System.out.println("Ukupan broj objavljenih oglasa u jednom danu ");
        oglasi.stream()
                .collect(Collectors.groupingBy(oglas -> oglas.objavljen))
                .entrySet()
                .stream()
                .sorted((o1, o2) -> o1.getKey().compareTo(o2.getKey()))
                .forEach(oglas -> System.out.printf("%s: %d%n", oglas.getKey(), oglas.getValue().size()));
    }
    private static void zadatak2() {    //stavio sam da je kategorija IT, da ne unosim rucno
        System.out.printf("%n%nProsječnu ponuđenu platu u zadatoj kategoriji%n IT = %.2f",
                oglasi.stream()
                        .filter(oglas -> oglas.kategorija == KategorijaPosla.IT)
                        .mapToDouble(oglas -> oglas.plata)
                        .average()
                        .getAsDouble());
    }
    private static void zadatak3() {
        Map.Entry<String, List<Oglas>> maxGradOglas =
                oglasi.stream()
                .collect(Collectors.groupingBy(oglas -> oglas.grad))
                .entrySet()
                .stream()
                .max((o1, o2) -> o1.getValue().size() - o2.getValue().size())
                .get();
        System.out.printf("%n%nGrad u kom se nudi najviše poslova: %s, %d",
                maxGradOglas.getKey(), maxGradOglas.getValue().size());
    }
    private static void zadatak4(){
        System.out.println("\n\nPrikaz svih oglasa grupisanih po godinama ");
        oglasi.stream()
                .collect(Collectors.groupingBy(oglas -> oglas.objavljen.getYear()))
                .entrySet()
                .stream()
                .forEach(entry -> {
                    System.out.println("Godina " + entry.getKey() + ":");
                    entry.getValue().stream()
                                        .forEach(System.out::println);
                });
    }
    private static void zadatak5() {
        System.out.println("\n\nPrikaz svih oglasa sortiranih po vremenu trajanja u opadajućem redoslijedu");
        oglasi.stream()
                .sorted((o1, o2) -> o1.trajanje - o2.trajanje)
                .forEach(System.out::println);
    }
    private static void zadatak6() {
        System.out.println("\n\nPrikaz najbolje plaćenog posla za svaku kategoriju");
        oglasi.stream()
                .collect(Collectors.groupingBy(oglas -> oglas.kategorija.toString()))
                .entrySet()
                .stream()
                .forEach(entry -> {
                    System.out.printf("%s - %s%n", entry.getKey(),
                            entry.getValue().stream()
                                    .max((o1, o2) -> (int)o1.plata - (int)o2.plata)
                                    .get());
                });
    }
    private static void zadatak7(){
        System.out.printf("%n%nProsječan broj godina radnog iskustva ukupno %.2f godina %n%n", oglasi.stream()
                .mapToInt(oglas -> oglas.iskustvo)
                .average()
                .getAsDouble());
    }
    private static void zadatak8() {
        System.out.println("Ukupan procenat broja remote poslova po kategorijama");
        oglasi.stream()
                .collect(Collectors.groupingBy(oglas -> oglas.kategorija))
                .entrySet()
                .stream()
                .forEach(oglas -> {
                    int ukupno = oglas.getValue().size();
                    long remotePoslovi = oglas.getValue().stream()
                                    .filter(oglas1 -> oglas1.nacinRada == NacinRada.REMOTE)
                                            .count();
                    System.out.printf("%s = %.2f%%%n", oglas.getKey(), (double) remotePoslovi / ukupno * 100);
                });
    }

    private static void generisi() {
        for(int i = 0; i < 60; i++) {
            if(i % 5 == 0)
                oglasi.add(new Oglas(i, KategorijaPosla.IT));
            else if(i % 5 == 1)
                oglasi.add(new Oglas(i, KategorijaPosla.EKONOMIJA));
            else if(i % 5 == 2)
                oglasi.add(new Oglas(i, KategorijaPosla.MEDICINA));
            else if(i % 5 == 3)
                oglasi.add(new Oglas(i, KategorijaPosla.NOVINARSTVO));
            else if(i % 5 == 4)
                oglasi.add(new Oglas(i, KategorijaPosla.PRAVO));
        }
    }
}