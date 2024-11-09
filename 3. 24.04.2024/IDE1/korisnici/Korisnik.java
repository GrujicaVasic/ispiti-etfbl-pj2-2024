package korisnici;

import knjige.Beletristika;
import knjige.Knjiga;
import knjige.KnjigaZaDjecu;
import knjige.StrucnaLiteratura;
import simulacija.Simulacija;

import java.io.*;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Random;

public class Korisnik extends Thread {
    public String ime, prezime;
    public int godine, brojKarte;
    public OblastInteresovanja interesovanje;
    private Knjiga[] red;
    private Random rand = new Random();
    private int pozicija = 0;
    public Object krajSimulacije;
    private final Simulacija sim;   //ovaj atribut sam treb'o ranije dodat', pa ne bi drugih stvari bilo...
    private int redUMatrici;    //ocajno.....

    public Korisnik(int redniBroj, Knjiga[] knjige, Simulacija sim) {
        ime = "Ime " + redniBroj;
        prezime = "Prezime " + redniBroj;
        godine = rand.nextInt(90) + 1;
        brojKarte = rand.nextInt(1000);
        interesovanje = new OblastInteresovanja();
        red = knjige;
        this.sim = sim;
        redUMatrici = redniBroj;
    }

    @Override
    public void run() {
        while(pozicija < Simulacija.BROJ_KOLONA) {
            try {
                if(imaKnjige()) {
                    System.out.printf("[%d] Korisnik: %s%nKnjiga: %s%n", pozicija, this, red[pozicija]);
                    try {
                        if (mozeLiPodiciKnjigu()) {
                            Knjiga k = red[pozicija];
                            System.out.printf("%nKorisnik [%s, %s, %d]: ",ime, prezime, brojKarte);
                            k.podigni();
                            upisIznajmljivanja(k);
                            sim.matrica[redUMatrici][pozicija] = null;
                        }
                    } catch(StarosnaDobException e) {
                        System.err.println(e.getMessage());
                    }
                }
                sleep(1000);
            } catch(InterruptedException e) {

            }
            finally { pozicija++; }
        }
        synchronized (krajSimulacije) {
            krajSimulacije.notify();
        }
    }
    @Override
    public String toString() {
        return String.format("ime=%s, prezime=%s, starost=%d, broj karte=%d, oblast interesovanja=%s",
                ime, prezime, godine, brojKarte, interesovanje);
    }
    private boolean imaKnjige() {
        return Objects.nonNull(red[pozicija]);
    }
    private boolean mozeLiPodiciKnjigu() throws StarosnaDobException {
        String tipKnjige = red[pozicija].getClass().getSimpleName();
        if("Beletristika".contentEquals(tipKnjige)) {
            Beletristika k = (Beletristika) red[pozicija];
            return k.zanr == interesovanje.zanr;
        } else if("KnjigaZaDjecu".contentEquals(tipKnjige)) {
            KnjigaZaDjecu k = (KnjigaZaDjecu) red[pozicija];
            if(godine != k.starost)
                throw new StarosnaDobException("Starosna dob korisnika nije u skladu sa prepoucenom dobi.");
        } else if("StucnaLiteratura".contentEquals(tipKnjige)) {
            StrucnaLiteratura k = (StrucnaLiteratura) red[pozicija];
            return k.oblast == interesovanje.oblast;
        }
        return false;
    }
    private void upisIznajmljivanja(Knjiga k) {
        String imeFajla = "BIBLIOTEKA-" + System.currentTimeMillis() + ".txt";
        try {
            FileWriter fw = new FileWriter("iznajmljivanja" + File.separator + imeFajla, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(String.format("%d#\"%s\"%n", brojKarte, k.naslov));
            pw.close();
        } catch (IOException e) {
            System.err.println("Problem sa upisom iznajmljivanja za knjigu " + k.naslov);
        }
    }
}
