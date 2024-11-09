import java.util.*;

public class Main {
	public static void main(String[] args) {
		LinkedList<Klijent> klijenti = new LinkedList<>();
		for(int i = 1; i <= 2000; i++) 
			klijenti.add(new Klijent(i));
		
		List<Klijent> rezultat = metoda(klijenti, 2);
		rezultat.forEach(System.out::println);
	}
	/*
		Trebalo bi da je ovo legitimno... Neka vrsta bubble sort-a.
	*/
	public static List<Klijent> metoda(List<Klijent> klijenti, int brojKlijenataSaMaxIznosom) {
		LinkedList<Klijent> rezultat = new LinkedList<>();
		for(int i = 0; i < brojKlijenataSaMaxIznosom; i++) {
			int najveciIznos = 0;
			int pozicijaNajveceg = 0;
			for(int j = 0; j < klijenti.size(); j++) {
				if(klijenti.get(j).iznos > najveciIznos) {
					najveciIznos = klijenti.get(j).iznos;
					pozicijaNajveceg = j;
				}
			}
			rezultat.add(klijenti.remove(pozicijaNajveceg));
		}
		return rezultat;
	}
}