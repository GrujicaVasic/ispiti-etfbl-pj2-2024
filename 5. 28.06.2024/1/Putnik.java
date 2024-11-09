import java.util.*;

public class Putnik {
	public String ime, prezime;
	
	private String[] imena = new String[]{"Milan", "Mladen", "Goran", "Teodor", "Milana", "Gorana", "Teodora",
	"Milica", "Rajo", "Marko"};
	private String[] prezimena = new String[] {"Milanovic", "Mladenovic", "Goranovic", "Vasic", "Djokic", "Petrovic", "Mihajlovic", "Jelic"};
	private Random rand = new Random();
	
	public Putnik() {
		ime = imena[rand.nextInt(imena.length)];
		prezime = prezimena[rand.nextInt(prezimena.length)];
	}
	@Override
	public String toString() {
		return ime + " " + prezime;
	}
}