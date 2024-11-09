import java.util.*;
import java.util.Timer;

public abstract class Vozilo extends Thread {
	private static int privremeno = 0;
	public List<Putnik> putnici = new LinkedList<>();
	public PoolPutnikaThread pool;
	public int id;
	public Object obj;
	
	protected Random rand = new Random();
	
	private long trajanjeSimulacije;
	private boolean jeKraj = false;
	
	
	public Vozilo() {
		generisiPutnike();
		trajanjeSimulacije = (rand.nextInt(11) + 20) * 1000L;
	}
	
	public abstract String ulaz();
	public abstract String izlaz();
	
	@Override
	public void run() {
		prekidSimulacije();
		while(!jeKraj) {
			int n = brojPutnika();
			if(rand.nextBoolean()) {
				pozoviPool(true, id, n);
				System.out.println(ulaz() + n);
			}
			else {
				pozoviPool(false, id, n);
				System.out.println(izlaz() + n);
			}
			try {
				sleep(2000);
			} catch(InterruptedException e) {
				System.out.println("CATCH run");
			}
		}
		synchronized(pool) {
			if(++pool.zavrsenihSimulacija == 20){
				System.out.println("Dragan serator " + id);
				pool.notify();
			}
		}
		System.out.println("Vozilo zavrsilo simulaciju, " + ++privremeno + " " + pool.zavrsenihSimulacija);
	}
	
	private synchronized void pozoviPool(boolean jeUlazakPutnika, int idVozila, int brojPutnika) {
		pool.jeUlazak = jeUlazakPutnika;
		pool.idVozila = idVozila;
		pool.brojPutnika = brojPutnika;
		synchronized(obj) {
			obj.notifyAll();
		}
		synchronized(pool) {
			try {
				pool.wait();
			} catch(InterruptedException e) {
				System.out.println("CATCH POZOVI POOL");
			}
		}
	}
	
	protected int brojPutnika() {
		return rand.nextInt(3) + 1;
	}
	
	private void generisiPutnike() {
		int brojPutnika = new Random().nextInt(26) + 25;
		for(int i = 0; i < brojPutnika; i++)
			putnici.add(new Putnik());
	}
	private void prekidSimulacije() {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				jeKraj = true;
			}
		}, trajanjeSimulacije);
	}
	
}