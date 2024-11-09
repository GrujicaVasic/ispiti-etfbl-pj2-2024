package clanovi;

import projekat.Zadatak;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class ScrumMaster extends Clan implements GeneratorZadataka {
    public boolean jePocetakSprinta = false;
    public ScrumMaster(String ime, String prezime, int godineRada) {
        super(ime, prezime, godineRada);
    }
    @Override
    public void run() {
        while(!sprint.jeKraj) {
            try {
                synchronized (this) {
                    wait();
                }
                if(jePocetakSprinta) {
                    sprint.dodajZadatke(generisiZadatake(sprint.iteracija));
                    System.out.printf("**%s %s generisao zadatke**%n%n", ime, prezime);
                    jePocetakSprinta = false;
                } else {
                    ispisSprinta();
                    synchronized (sprint) {
                        sprint.notify();
                    }
                }
            } catch(InterruptedException e) {

            }
        }
    }
    private void ispisSprinta() {
        String imeFajla = "sprint_" + sprint.iteracija + ".txt";
        try {
            FileWriter fw = new FileWriter(imeFajla, true);
            PrintWriter pw = new PrintWriter(fw);
            LinkedList<String> ispis = new LinkedList<>();
            for(Developer d : sprint.tim.developeri) {
                d.uradjeniZadaci.stream()
                        .filter(zadatak -> zadatak.brojIteracije == sprint.iteracija)
                        .forEach(zadatak -> ispis.add(String.format("%s - %s", zadatak, d)));
            }
            ispis.stream().forEach(pw::println);
            pw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
