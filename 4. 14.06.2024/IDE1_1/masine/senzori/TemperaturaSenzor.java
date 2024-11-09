package masine.senzori;

import masine.Masina;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

public class TemperaturaSenzor extends Senzor {
    public LinkedList<Double> mjerenja = new LinkedList<>();

    public TemperaturaSenzor(Masina masina) {
        super("Senzor za temperaturu", masina);
        logFajl += "temperatura.txt";
    }
    @Override
    public String ispis() {
        return String.format("Temperatura %.2f", mjerenje);
    }
    @Override
    protected void radi() {
        mjerenje = rand.nextDouble(gornjaGranicaMjerenja);
        if(mjerenja.size() == 11)
            mjerenja.remove();
        mjerenja.add(mjerenje);
        upisULogFajl(String.format("%.2f", mjerenje));
    }
}
