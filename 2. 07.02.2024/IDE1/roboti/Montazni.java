package roboti;

public class Montazni extends Robot {
    public Montazni(int serijskiBroj) {
        super(serijskiBroj, TipRobota.MONTAZNI);
    }

    @Override
    public String pocetakAkcije() {
        return String.format("Robot %d je u statusu MONTAZE: %s", serijskiBroj, super.toString());
    }
    @Override
    public String krajAkcije() {
        return String.format("\n###Robot %d zavrsio sa MONTAZOM", serijskiBroj);
    }
}
