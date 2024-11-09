package osobe;

import java.util.ArrayList;

import simulation.Simulation;

public abstract class Zaposleni extends Thread {
	final long vrijemeRada;
	Simulation simulation;
	ArrayList<String> uradjeniZadaci;
	
	String ime;
	String prezime;
	int godineRada;
	int brojacPoruka;
	double cijenaRada;
	
	public boolean isKraj;
	
	public Zaposleni(String ime, String prezime, int godineRada, double cijenaRada, long vrijemeRada, Simulation simulation) {
		this.ime = ime;
		this.prezime = prezime;
		this.godineRada = godineRada;
		this.cijenaRada = cijenaRada;
		this.vrijemeRada = vrijemeRada;
		this.simulation = simulation;
		brojacPoruka = 0;
		isKraj = false;
		uradjeniZadaci = new ArrayList<>();
	}
	
	public abstract void radi();
	
	public void pauza() {
		try {
			System.out.println(String.format("Radnik %s %s ide na pauzu od 5 sekundi.", ime, prezime));
			sleep(5000L);
			System.out.println(String.format("Radnik %s %s zavrsio sa pauzom.", ime, prezime));
			interrupted();
		}
		catch(InterruptedException e) { }
	}
	
	protected void pocetakRada() {
		System.out.println(String.format("Radnik %s %s pocinje sa radom.", ime, prezime));
	}
	
	public String getIme(){ return ime; }
	public String getPrezime() { return prezime; }
}