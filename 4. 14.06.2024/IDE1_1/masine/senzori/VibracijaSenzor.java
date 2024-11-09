package masine.senzori;

import masine.Masina;

public class VibracijaSenzor extends Senzor {
    public double pretodnoMjerenje;
    public VibracijaSenzor(Masina masina) {
        super("Senzor za vibracije", masina);
        logFajl += "vibracije.txt";
    }

    @Override
    public String ispis() {
        return String.format("Vibracije %.2f", mjerenje);
    }
    @Override
    protected void radi() {
        pretodnoMjerenje = mjerenje;
        mjerenje = rand.nextDouble(gornjaGranicaMjerenja);
        if(opasneVibracije())
            System.err.println("\nOcitane su opasne vibracije!\n");
        upisULogFajl(String.format("%.2f", mjerenje));
    }

    public boolean opasneVibracije() {
        return mjerenje > 2 * pretodnoMjerenje;
    }
}
