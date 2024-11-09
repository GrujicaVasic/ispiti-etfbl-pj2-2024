package masine.senzori;

import masine.Masina;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;

public class PritisakSenzor extends Senzor {
    public ArrayDeque<Double> mjerenja = new ArrayDeque<>(5);

    public PritisakSenzor(Masina masina) {
        super("Senzor za pritisak", masina);
        logFajl += "pritisak.txt";
    }

    @Override
    public String ispis() {
        return String.format("Pritisak %.2f", mjerenje);
    }
    @Override
    protected void radi() {
        mjerenje = rand.nextDouble(gornjaGranicaMjerenja);
        if(mjerenja.size() == 6)
            mjerenja.remove();
        mjerenja.add(mjerenje);
        String upis = String.format("trenuta=%.2f, sr. vrij.=%.2f", mjerenje, prosjekPritiska());
        upisULogFajl(upis);
    }

    public double prosjekPritiska() {
        return mjerenja.stream().limit(5).mapToDouble(mapper -> mapper).average().getAsDouble();
    }
}
