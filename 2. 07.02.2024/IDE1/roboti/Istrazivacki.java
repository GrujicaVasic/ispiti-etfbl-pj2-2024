package roboti;

public class Istrazivacki extends Robot {
    public Istrazivacki(int serijskiBroj) {
        super(serijskiBroj, TipRobota.ISTRAZIVACKI);
    }

    @Override
    public String pocetakAkcije() {
        return String.format("Robot %d je u statusu ISTRAZIVANJA: %s", serijskiBroj, super.toString());
    }
    @Override
    public String krajAkcije() {
        return String.format("\n###Robot %d zavrsio sa ISTRAZIVANJA", serijskiBroj);
    }
}
