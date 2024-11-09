import java.time.*;
import java.util.Random;

public class Automobil {
	public int godina, vrata, snaga;
	public String marka, model;
	public TipAutomobila tip;
	
	private Random rand = new Random();
	private TipAutomobila[] tipovi = TipAutomobila.values();
	private String[] marke = new String[] {"Toyota", "VW", "Audi", "BMW"};
	private String[] modeli = new String[] {"Corolla", "Passat", "S5", "M3"};
	
	public Automobil() {
		int indeks = rand.nextInt(marke.length);
		marka = marke[indeks];
		model = modeli[indeks];
		tip = tipovi[rand.nextInt(tipovi.length)];
		godina = LocalDate.now().getYear() - rand.nextInt(20);
		vrata = rand.nextInt(2) + 2;
		snaga = rand.nextInt(400) + 100;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Automobil) {
			Automobil a = (Automobil) o;
			return a.tip == tip && a.godina == godina 
				&& a.vrata == vrata && a.snaga == snaga 
				&& a.marka.contentEquals(marka) && a.model.contentEquals(model);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return String.format("%s, %s, %d, %d, %d, %s", marka, model, godina, vrata, snaga, tip.toString());
	}
}