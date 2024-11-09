import java.util.*;

public class Tim {
	public ProductOwner ow;
	public ScrumMaster sm;
	public List<Developer> developeri = new LinkedList<>();
	
	public Tim() {
		ow = new ProductOwner("Grujica", "Vasic", 23, "Sve najbolje o doticnom");
		sm = new ScrumMaster("Nazario", "Lima", 14);
		developeri.add(new Developer("Zinedine", "Zidane", 10));
		developeri.add(new Developer("Dejan", "Savicevic", 7));
		developeri.add(new Developer("Sinisa", "Mihajlovic", 6));
	}
}