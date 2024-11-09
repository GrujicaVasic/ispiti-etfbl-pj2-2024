package komponente;

public class Motor extends Komponenta {
    public int snaga;
    public Motor(int id, String proizvodjac, int godinaProizvodnje, int snaga) {
        super(id, proizvodjac, godinaProizvodnje);
        this.snaga = snaga;
    }

    @Override
    public String toString() {
        return String.format("Motor: %s, snaga=%d", super.toString(), snaga);
    }
}
