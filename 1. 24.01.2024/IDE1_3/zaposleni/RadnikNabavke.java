package zaposleni;

import simulacija.Simulacija;

public class RadnikNabavke extends Zaposleni {
    public RadnikNabavke(String ime, String prezime, int staz, double cijena, Simulacija sim) {
        super(ime, prezime, staz, cijena, sim);
    }

    @Override
    public String radi() {
        return "Upit za nabavku #" + brojac++;
    }
}
