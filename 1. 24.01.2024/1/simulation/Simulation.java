package simulation;

import java.util.*;

import osobe.*;

public class Simulation {
	RadnikProdaje rp;
	RadnikNabavke rn;
	Racunovodja r;
	
	protected boolean prodajaTrigger;
	protected boolean nabavkaTrigger;
	
	public Simulation() {
		rp = new RadnikProdaje("Radnik", "Prodaje", 2, 15.00, this);
		odrediPauzu(rp);
		rn = new RadnikNabavke("Radnik", "Nabavke", 3, 17.00, this);
		odrediPauzu(rn);
		r = new Racunovodja("Radnik", "Racunovodja", 1, 19.00, this);
		odrediPauzu(r);
		prodajaTrigger = nabavkaTrigger = false;
		Thread r1 = new Thread(rp::radi);
		Thread r2 = new Thread(rn::radi);
		Thread r3 = new Thread(r::radi);
		r1.start();
		r2.start();
		r3.start();
	}
		
	public void obracunaj(Zaposleni zaposleni) {
		String pozivalacObracuna = zaposleni.getClass().getSimpleName();
		if("RadnikNabavke".contentEquals(pozivalacObracuna)) {
			nabavkaTrigger = true;
			System.out.println("NABAVKA SETOVALA");
		}
		else if("RadnikProdaje".contentEquals(pozivalacObracuna)) {
			prodajaTrigger = true;
			System.out.println("PRODAJA SETOVALA");
		}
		if(nabavkaTrigger && prodajaTrigger) {
			System.out.println("SIMULATION, IF TRIGGERI");
			r.isPauza = false;
			r.interrupted();
			nabavkaTrigger = prodajaTrigger = false;
		}
	}
	
	public void prekid() {
		rp.isKraj = rn.isKraj = r.isKraj = true;
	}
	public RadnikProdaje getRadnikProdaje() {
		return rp;
	}
	public RadnikNabavke getRadnikNabavke() {
		return rn;
	}
	
	private void odrediPauzu(Zaposleni zaposleni) {
		Random random = new Random();
		if(random.nextBoolean()) {
			Timer zadatak = new Timer();
			int pocetakPauze = random.nextInt(15) + 5;
			zadatak.schedule(new TimerTask() {
				public void run() {
					if("Racunovodja".contentEquals(zaposleni.getClass().getSimpleName()))
						r.isPauza = true;
					zaposleni.interrupt();
				}
			}, pocetakPauze * 1000L);
		}
	}
}