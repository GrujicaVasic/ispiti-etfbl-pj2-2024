package simulacija;

import knjige.Beletristika;
import knjige.Knjiga;
import knjige.KnjigaZaDjecu;
import knjige.StrucnaLiteratura;
import korisnici.Korisnik;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Simulacija extends Thread {
    public static final int BROJ_KOLONA = 20;
    public static final int BROJ_KORISNIKA = 10;
    public ArrayList<Korisnik> korisnici = new ArrayList<>(10);
    private Knjiga[] knjige;
    public Knjiga[][] matrica = new Knjiga[BROJ_KORISNIKA][BROJ_KOLONA];
    private final Object obavjestenje = new Object();
    private int brojZavrsenihKorisinka = 0;
    private long pocetak, kraj;
    private String statistika;

    public Simulacija(int brojKnjiga) {
        knjige = new Knjiga[brojKnjiga];
        generisiKnjige(brojKnjiga);
        generisiKorisnike();
    }

    @Override
    public void run() {
        pocetak = System.currentTimeMillis();
        korisnici.forEach(Thread::start);
        while(brojZavrsenihKorisinka < BROJ_KORISNIKA) {
            synchronized (obavjestenje) {
                try {
                    obavjestenje.wait();
                    brojZavrsenihKorisinka++;
                    //System.out.println();
                } catch (InterruptedException e) { }
            }
        }
        kraj = System.currentTimeMillis();
        upisSimulacijeUFajl();
        System.out.printf("Procenat podignutih knjiga je %.2f%% %n", racunanjeProcenta());
        citanjeStatistike();
        System.out.println("\n\nSIMULACIJA ZAVRSENA\n\n");
    }

    private void generisiKnjige(int brojKnjiga) {
        knjige = new Knjiga[brojKnjiga];
        int granica = brojKnjiga - (brojKnjiga % 3);
        for(int i = 0; i < granica; i++) {
            if(i < granica / 3)
                knjige[i] = new Beletristika(i);
            else if(i < granica / 3 * 2)
                knjige[i] = new KnjigaZaDjecu(i);
            else if(i < granica / 3 * 3)
                knjige[i] = new StrucnaLiteratura(i);
        }
        postaviKnjige(granica);
    }
    private void postaviKnjige(int brojInstanciranihKnjiga) {
        Random rand = new Random();
        for(int i = 0; i < brojInstanciranihKnjiga; i++) {
            boolean knjigaDodata = false;
            while(!knjigaDodata) {
                int x = rand.nextInt(BROJ_KOLONA);
                int y = rand.nextInt(10);
                if(matrica[y][x] == null) {
                    matrica[y][x] = knjige[i];
                    knjigaDodata = true;
                }
            }
        }
    }
    private void generisiKorisnike() {
        for(int i = 0; i < 10; i++) {
            korisnici.add(new Korisnik(i, matrica[i], this));
            korisnici.get(i).krajSimulacije = obavjestenje;
        }
        //ne znam treba li bukvalno pravit' struktura koja predstavlja biblioteku pa da se po njoj krecu korisnici, tj. Korisnik[] biblioteka
        //kod mene ce se kretat' bez toga...
    }
    private void upisSimulacijeUFajl() {
        try {
            statistika = "BIBLIOTEKA-" + System.currentTimeMillis() + ".txt";
            PrintWriter pw = new PrintWriter(statistika);
            long vrijeme = (kraj - pocetak) / 1000;
            pw.println(String.format("Datum %s%nVrijeme trajanja simulacije %d [s]%nProcenat podignutih knjiga %.2f%%",
                    LocalDate.now().toString(), vrijeme, racunanjeProcenta()));
            pw.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void citanjeStatistike() {
        try {
            List<String> fajl = Files.readAllLines(Path.of(statistika));
            System.out.println("Fajl sa statistikom:");
            fajl.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private double racunanjeProcenta() {
        int brojac = 0;
        for(int i = 0; i < BROJ_KORISNIKA; i++)
            for(int j = 0; j < BROJ_KOLONA; j++)
                if(matrica[i][j] != null)
                    brojac++;
        return (100.00 * (double)(knjige.length - brojac)) / (double)knjige.length;
    }
}
