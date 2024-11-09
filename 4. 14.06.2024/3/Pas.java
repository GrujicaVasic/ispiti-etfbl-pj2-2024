import java.util.*;
import java.time.LocalDate;


public class Pas {
	public int godina, tezina;
	public String ime;
	public OmiljenaHrana hrana;
	
	private String[] imena = new String[] {"Max", "Rex", "Bobi", "Mile", "Dzeki", "Dzoni", "Smirnof"};
	private OmiljenaHrana[] menu = OmiljenaHrana.values();
	private Random rand = new Random();
	
	public Pas() {
		ime = imena[rand.nextInt(menu.length)];
		tezina = rand.nextInt(30) + 2;
		godina = LocalDate.now().getYear() - rand.nextInt(20);
		hrana = menu[rand.nextInt(menu.length)];
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Pas p) 
			return godina == p.godina && ime.contentEquals(p.ime);
		return false;
	}
	@Override
	public String toString() {
		return String.format("%s, %d, %d, %s", ime, godina, tezina, hrana.toString());
	}
}