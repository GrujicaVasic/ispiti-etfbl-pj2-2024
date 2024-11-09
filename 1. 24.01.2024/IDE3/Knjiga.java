import java.util.Random;

public class Knjiga {
    public String naslov, ime, prezime;
    private final Random rand = new Random();
    public Knjiga(int brojPolice) {
        naslov = "Naslov " + brojPolice + rand.nextInt(501);
        int broj = rand.nextInt(Autori.imena.length);
        ime = Autori.imena[broj];
        prezime = Autori.prezimena[broj];
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", naslov, ime, prezime);
    }
}

class Autori {
    public static final String[] imena = new String[] {"Ivo", "Branko", "Branislav", "Radoje",
            "Desanka", "Mesa", "Milos", "Stiven", "Aleksandar", "Fjodor", "Lav", "Ernest",
            "Herman", "Dzordz"};
    public static final String[] prezimena = new String[] {"Andric", "Copic", "Nusic", "Domanovic",
            "Maksimovic", "Selimovic", "Crnjanski", "King", "Dima", "Dostojevski", "Tolstoj", "Hemingvej",
            "Hese", "Orvel"};
}