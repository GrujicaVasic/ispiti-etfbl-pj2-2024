import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.*;

public class Main {
	public static HashSet<Pas> psi = new HashSet<>();
	public static HashSet<Pas> psi1 = new HashSet<>();
	
	public static void main(String[] args) {
		generisiPse();
		zadatak1();
		zadatak2();
		zadatak3();
		zadatak4();
		zadatak5();
	}
	
	public static void zadatak1() {	//vjerovatno nije tacno...
		System.out.println("Spajanje grupe pasa:");
		/*System.out.println("1. grupa:");
		psi.stream().forEach(System.out::println);
		System.out.println("2. grupa:");
		psi1.stream().forEach(System.out::println);
		System.out.println("Konkat:");*/
		LinkedList<Pas> psiFilter = new LinkedList<>(psi1);
		Stream.concat(psi.stream(), psi1.stream())
				.filter(pas -> !psiFilter.contains(pas))
				.forEach(System.out::println);
	}
	
	public static void zadatak2() {
		System.out.println("\nFiltiranje grupe pasa:");
		psi.stream()
					.filter(pas -> pas.godina > 2005 && pas.godina < 2014)
					.collect(Collectors.groupingBy(pas -> pas.godina))
					.entrySet()
					.stream()
					.forEach(System.out::println);
	}
	public static void zadatak3() {
		System.out.println("\nSortiranje grupe pasa po omiljenoj hrani:");
		psi.stream()
					.sorted((p1, p2) -> p1.hrana.toString().compareTo(p2.hrana.toString()))
					.forEach(System.out::println);
	}
	public static void zadatak4() {
		System.out.printf("%nSumirati težine svih pasa iz grupe kojima je omiljena hrana PILETINA i godina rođenja je parna: %d%n",
		psi.stream()
					.filter(pas -> pas.hrana == OmiljenaHrana.PILETINA && (pas.godina % 2 ==0))
					.mapToInt(pas -> pas.tezina)
					.sum());
		
	}
	public static void zadatak5() {
		System.out.println("\nPrikazati psa sa najmanje godina, najviše godina i najbližeg prosječnoj godini:");
		System.out.printf("Najmanje godia: %s%n", psi.stream()
					.sorted((p1, p2) -> p1.godina - p2.godina)
					.max((p1, p2) -> p1.godina - p2.godina).get());
		System.out.printf("Najvise godina: %s%n", psi.stream()
					.sorted((p1, p2) -> p1.godina - p2.godina)
					.min((p1, p2) -> p1.godina - p2.godina).get());
		double prosjek = psi.stream()
					.mapToInt(pas -> pas.godina)
					.average().getAsDouble();
		System.out.printf("Prosjek godina rodjenja: %.2f%n", prosjek);
		System.out.printf("Najblizi prosjecnoj godini rodjenja: %s", 
						psi.stream()
							.min((p1, p2) -> Math.abs((int) prosjek - p1.godina) - Math.abs((int) prosjek - p2.godina))
							.get());
	}
	
	private static void generisiPse() {
		for(int i = 0; i < 10; i++)
			psi.add(new Pas());
		for(int i = 0; i < 10; i++)
			psi1.add(new Pas());
	}
}