import java.util.*;

public class Zadatak {
	private static int brojacZadataka = 1;
	
	public String naslov, opis;
	public int prioritet;
	public VrstaZadatka vrsta;
	public boolean jeUradjen = false;
	
	private Random rand = new Random();
	private VrstaZadatka[] vrste = VrstaZadatka.values();
	
	public Zadatak() {
		naslov = "Naslov " + brojacZadataka;
		opis = "Opis " + brojacZadataka++;
		prioritet = rand.nextInt(5) + 1;
		vrsta = vrste[rand.nextInt(vrste.length)];
	}
}