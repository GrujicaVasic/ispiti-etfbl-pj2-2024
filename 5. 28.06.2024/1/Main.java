import java.util.*;

public class Main {
	public static void main(String[] args) {
		LinkedList<Vozilo> vozila = new LinkedList<>();
		for(int i = 0; i < 20; i++) {
			if(i % 4 == 0) 
				vozila.add(new Gradski());
			else if(i % 4 == 1) 
				vozila.add(new Prigradski());
			else if(i % 4 == 2) 
				vozila.add(new SaVagonima());
			else if(i % 4 == 3) 
				vozila.add(new BezVagona());
		}
		PoolPutnikaThread pool = new PoolPutnikaThread(vozila);
		for(Vozilo v : vozila) 
			v.pool = pool;
		pool.start();
	}
}