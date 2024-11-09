package komponente;

public class Baterija extends Komponenta {
    public int kapacitet;
    public Baterija(int id, String proizvodjac, int godinaProizvodnje, int kapacitet) {
        super(id, proizvodjac, godinaProizvodnje);
        this.kapacitet = kapacitet;
    }
    @Override
    public String toString() {
        return String.format("Baterija: %s, kapacitet=%d", super.toString(), kapacitet);
    }
}
