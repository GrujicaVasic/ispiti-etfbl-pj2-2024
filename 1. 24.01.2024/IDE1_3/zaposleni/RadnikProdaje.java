package zaposleni;

import simulacija.Simulacija;

public class RadnikProdaje extends Zaposleni {
    public RadnikProdaje(String ime, String prezime, int staz, double cijena, Simulacija sim) {
        super(ime, prezime, staz, cijena, sim);
    }

    @Override
    public String radi() {
        return "Ponuda za prodaju #" + brojac++;
    }
}
