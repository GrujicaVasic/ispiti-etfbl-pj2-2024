package komponente;

public abstract class Komponenta extends Thread {
    public int id, godinaProizvodnje;
    public String proizvodjac;

    public Komponenta(int id, String proizvodjac, int godinaProizvodnje) {
        this.id = id;
        this.proizvodjac = proizvodjac;
        this.godinaProizvodnje = godinaProizvodnje;
    }

    @Override
    public String toString() {
        return String.format("id=%d, godina=%d, proizvodjac=%s", id, godinaProizvodnje, proizvodjac);
    }
}
