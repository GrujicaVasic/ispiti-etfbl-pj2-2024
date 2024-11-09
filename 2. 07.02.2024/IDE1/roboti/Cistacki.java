package roboti;

public class Cistacki extends Robot {
    public Cistacki(int serijskiBroj) {
        super(serijskiBroj, TipRobota.CISTACKI);
    }

    @Override
    public String pocetakAkcije() {
        return String.format("Robot %d je u statusu CISCENJA: %s", serijskiBroj, super.toString());
    }
    @Override
    public String krajAkcije() {
        return String.format("\n###Robot %d zavrsio sa CISCENJEM", serijskiBroj);
    }
}
