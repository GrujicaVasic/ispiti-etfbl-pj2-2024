import java.util.Random;

public class Baterija {
	public int stanje;
	
	@Override
	public String toString() {
		return String.valueOf(new Random().nextInt(51) + 50);
	}
}