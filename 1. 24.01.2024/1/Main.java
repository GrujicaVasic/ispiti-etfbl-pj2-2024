import java.util.*;
import osobe.*;
import simulation.Simulation;

public class Main {
	public static void main(String[] args) {
		Simulation s = new Simulation();
		String kraj = "";
		Scanner scan = new Scanner(System.in);
		
		while(!"kraj".contentEquals(kraj)) {
			kraj = scan.nextLine();
		}
		
		s.prekid();
		System.out.println("Kraj simulacije");
	}
}