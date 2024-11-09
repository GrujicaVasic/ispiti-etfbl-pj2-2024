package osobe;

import simulation.Simulation;

public class RadnikNabavke extends Zaposleni {
	public RadnikNabavke(String ime, String prezime, int godineRada, double cijenaRada, Simulation simulation) {
		super(ime, prezime, godineRada, cijenaRada, 3000, simulation);
	}
	
	public void radi() {
		pocetakRada();
		String posao = "";
		while(!isKraj) {
			posao = "Upit za nabavku # " + (++brojacPoruka);
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
