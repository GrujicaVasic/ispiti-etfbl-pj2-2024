package masine.senzori;

import masine.Masina;

public class VlagaSenzor extends Senzor {
    public VlagaSenzor(Masina masina) {
        super("Senzor za vlagu", masina);
        logFajl += "vlaga.txt";
    }

    @Override
    public String ispis() {
        return String.format("Vlaga %.2f", mjerenje);
    }

    @Override
    protected void radi() {
        mjerenje = rand.nextDouble(gornjaGranicaMjerenja);
        upisULogFajl(String.format("%.2f", mjerenje));
    }
}
