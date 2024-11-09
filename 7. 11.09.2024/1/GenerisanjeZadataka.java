import java.util.concurrent.*;

public interface GenerisanjeZadataka {
	int BROJ_ZADATAKA = 5;
	
	default PriorityBlockingQueue<Zadatak> generisi() {
		PriorityBlockingQueue<Zadatak> zadaci = new PriorityBlockingQueue<>(BROJ_ZADATAKA, (z1, z2) -> z1.prioritet - z2.prioritet);
		for(int i = 0; i < BROJ_ZADATAKA; i++)
			zadaci.add(new Zadatak());
		return zadaci;
	}
}