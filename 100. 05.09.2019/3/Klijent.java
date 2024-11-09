import java.util.*;
import java.time.LocalDate;

public class Klijent {
	private static Random rand = new Random();
	private static int godina = LocalDate.now().getYear();
	
	public String ime, prezime, id;
	public int iznos;
	
	private String[] imena = new String[] {"Marija", "Dragana", "Tamara", "Tatjana", "Teodora", "Milena", "Gorana", "Goran", "Milan",
			"Mladen", "Dragan", "Jovan", "Marko", "Rajo", "Pero", "Branko", "Ljubivoje"};
	private String[] prezimena = new String[] {"Marjanovic", "Dragic", "Teodorovic", "Goranovic", "Vasic", "Goranovic", "Milanovic", "Mladenovic",
			"Jovanovic", "Markovic", "Peric", "Stosic"};
	
	
	public Klijent(int redniBroj) {
		ime = imena[rand.nextInt(imena.length)];
		prezime = prezimena[rand.nextInt(prezimena.length)];
		id = redniBroj + "-" + godina;
		iznos = rand.nextInt(29_000) + 1_000;
	}
	
	@Override
	public String toString() {
		return String.format("%s, %s, %s, %d", id, ime, prezime, iznos);
	}
}