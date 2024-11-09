import java.util.*;


public class Main {
	public static LinkedList<Automobil> auta = new LinkedList<Automobil>();
	
	public static void main(String[] args) {
		generisi();
		zadatak2();
		zadatak3();
	}
	
	public static void zadatak2() {
		System.out.println("\n\nSortiranje grupe automobila po snazi motora od veće ka manjoj");
		auta.stream()
				.sorted((a1, a2) -> a2.snaga - a1.snaga)
				.forEach(System.out::println);
	}
	public static void zadatak3() {
		System.out.println("\n\nSumiranje kapaciteta vrata svih automobila iz grupe koji su tipa hatchback i imaju više od dvoje vrata");
		System.out.printf("Suma vrata je %d", auta.stream()
					.filter(auto -> auto.tip == TipAutomobila.HATCHBACK && auto.vrata > 2)
					.mapToInt(auto -> auto.vrata)
					.sum());
	}
	
	private static void generisi() {
		for(int i = 0; i < 15; i++)
			auta.add(new Automobil());
	}
}