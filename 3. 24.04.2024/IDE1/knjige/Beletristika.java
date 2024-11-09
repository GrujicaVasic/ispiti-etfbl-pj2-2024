package knjige;

public class Beletristika extends Knjiga{
    public Zanrovi zanr;
    public Beletristika(int redniBroj) {
        super(redniBroj);
        Zanrovi[] zanrovi = Zanrovi.values();
        zanr = zanrovi[rand.nextInt(zanrovi.length)];
    }
    @Override
    public void podigni() {
        StringBuilder sb = new StringBuilder(String.format("Knjiga %s, autora %s, podignuta ", naslov, autor));
        String dove = rand.nextBoolean() ? "U BIBLIOTECI" : "ONLINE";
        System.out.println(sb);
    }
}
