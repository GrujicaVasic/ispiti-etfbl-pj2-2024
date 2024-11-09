package komponente;

public class Senzor extends Komponenta {
    VrstaSenzora vrsta;
    public int raspon;
    public Senzor(int id, String proizvodjac, int godinaProizvodnje, VrstaSenzora vrsta, int raspon) {
        super(id, proizvodjac, godinaProizvodnje);
        this.vrsta = vrsta;
        this.raspon = raspon;
    }
    @Override
    public String toString() {
        return String.format("Senzor: %s, vrsta=%s, raspon=%d", super.toString(),
                vrsta.toString(), raspon);
    }
}
