package osobe;

import simulation.Simulation;

public class RadnikProdaje extends Zaposleni {
	public RadnikProdaje(String ime, String prezime, int godineRada, double cijenaRada, Simulation simulation) {
		super(ime, prezime, godineRada, cijenaRada, 3000, simulation);
	}
	
	public void radi() {
		pocetakRada();
		String posao = "";
		while(!isKraj) {
			posao = "Ponuda za prodaju # " + (++brojacPoruka);
			System.out.println(posao);
			uradjeniZadaci.add(posao);
			if(brojacPoruka == 10)
				simulation.obracunaj(this);
			if(isInterrupted())
				pauza();
			try {
				sleep(vrijemeRada);
			} catch(InterruptedException e) {
				pauza();
			}
		}
	}
}