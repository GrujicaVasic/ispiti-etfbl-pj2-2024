package knjige;

public class StrucnaLiteratura extends Knjiga {
    public Oblasti oblast;
    public StrucnaLiteratura(int redniBroj) {
        super(redniBroj);
        Oblasti[] oblasti = Oblasti.values();
        oblast = oblasti[rand.nextInt(oblasti.length)];
    }
    @Override
    public void podigni() {
        System.out.printf("Knjiga %s, autora %s, podignuta ONLINE%n", naslov, autor);
    }
}
