import java.util.*;
import java.time.LocalDate;

public class Stek {
	public Stack<Knjiga> knjige = new Stack<>();
	
	class Knjiga {
		public String naslov, autor;
		public int stranice, godina;
		
		private String[] imena = new String[] { "Ivo", "Petar", "Branko", "Milan", "Mladen", "Goran", "Ivica", "Stevica"};
		private String[] prezimena = new String[] {"Andric", "Kocic", "Copic", "Radicevic", "Rakic", "Mladenovic", "Goranovic"};
		
		public Knjiga() {
			Random rand = new Random();
			naslov = "Naslov " + rand.nextInt(5000);
			autor = prezimena[rand.nextInt(prezimena.length)] + ", " + imena[rand.nextInt(imena.length)];
			stranice = rand.nextInt(500) + 50;
			godina = LocalDate.now().getYear() - rand.nextInt(100);
		}		
		@Override
		public String toString() {
			return String.format("%s; %s; stranice=%d; godina izdanja=%d", naslov, autor, stranice, godina);
		}
	}
	public void dodaj(Knjiga k) {
		knjige.push(k);
		
	}
	public Knjiga ukloni() throws StekPrazanException {
		if(knjige.isEmpty())
			throw new StekPrazanException();
		return knjige.pop();
	}
}