package clanovi;

import projekat.Sprint;

public abstract class Clan extends Thread {
    public String ime, prezime;
    public int godineRada;
    public Sprint sprint;
    public Clan(String ime, String prezime, int godineRada) {
        this.ime = ime;
        this.prezime = prezime;
        this.godineRada = godineRada;
    }
}
