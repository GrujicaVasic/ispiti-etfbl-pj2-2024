package knjige;

public class KnjigaZaDjecu extends Knjiga {
    public int starost;
    public KnjigaZaDjecu(int redniBroj) {
        super(redniBroj);
        starost = rand.nextInt(18);
    }
    @Override
    public void podigni() {
        System.out.printf("Knjiga %s, autora %s, podignuta U BIBLIOTECI.%n", naslov, autor);
    }
}
