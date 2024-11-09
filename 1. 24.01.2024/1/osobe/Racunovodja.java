package osobe;

import java.util.*;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

import simulation.Simulation;

public class Racunovodja extends Zaposleni {
	public boolean isPauza;	//ovo onda komplikuje - mogu oba biti true pa neces znati sta je u pitanju. Bolje imati samo jedan boolean, pa ako je
						//ovaj setovan, onda je obracun u pitanju, u suportonom je pauza razlog interrupt-a.
	//public boolean isObracun;
	
	public Racunovodja(String ime, String prezime, int godineRada, double cijenaRada, Simulation simulation) {
		super(ime, prezime, godineRada, cijenaRada, 10000, simulation);
		isPauza = false;
	}
	
	public void radi() {
		pocetakRada();
		String posao = "";
		while(!isKraj) {
			posao = "Obracun troskova # " + (++brojacPoruka);
			System.out.println(posao);
			uradjeniZadaci.add(posao);
			
			if(isInterrupted())
				obradaPrekida();
			try {
				sleep(vrijemeRada);
			} catch(InterruptedException e) { 
				interrupted();
				obradaPrekida();
			}	
		}
	}
	private void obradaPrekida() {
		if(isPauza) {
			isPauza = false;
			pauza();
		}
		else obracun();
	}
	
	private void obracun() {
		StringBuilder sb = new StringBuilder();
		RadnikProdaje rp = simulation.getRadnikProdaje();
		double iznos = rp.cijenaRada + rp.godineRada;
		sb.append(rp.ime + " " + rp.prezime + ", " + String.valueOf(iznos));
		
		RadnikNabavke rn = simulation.getRadnikNabavke();
		iznos = rn.cijenaRada + rn.godineRada;
		sb.append(rn.ime + " " + rn.prezime + ", " + String.valueOf(iznos));
		
		try {
			System.out.println("ISPIS OBRACUNA");
			PrintWriter pw = new PrintWriter(new FileWriter("C:\\Users\\Strat\\Desktop\\Rokovi\\24.01.2024\\1\\obracun.txt"));	//C:\\Users\\Strat\\Desktop\\Rokovi\\24.01.2024\\1\\
			
			pw.print(sb.toString());
			pw.close();
		} catch(IOException e) {
			System.out.println("Nema fajla!");
		}
	}
}