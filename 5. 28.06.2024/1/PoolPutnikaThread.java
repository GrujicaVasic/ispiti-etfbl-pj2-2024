import java.util.*;
import java.io.*;
import java.util.stream.*;

public class PoolPutnikaThread extends Thread {
	public List<Putnik> putnici = new LinkedList<>();
	public List<Vozilo> vozila;
	public Object obj = new Object();
	
	public int idVozila, brojPutnika, zavrsenihSimulacija = 0;
	public boolean jeUlazak;
	
	private Random rand = new Random();
	
	public PoolPutnikaThread(List<Vozilo> vozila) {
		this.vozila = vozila;
		for(Vozilo v : vozila)
			v.obj = obj;
		int brojPutnika = rand.nextInt(10) + 100;
		for(int i = 0; i < brojPutnika; i++)
			putnici.add(new Putnik());
	}
	
	@Override
	public void run() {
		pokreniVozila();
		while(zavrsenihSimulacija < vozila.size()) {
			synchronized(obj) {
				try {
					obj.wait();
				} catch(InterruptedException e) { }
			}
			synchronized(this) {
				try {
					if(jeUlazak)
						dodajPutnike();
					else 
						izbaciPutnike();
					notifyAll();
				} catch(BrojnoStanjeException e) { }
			}
		}
		upisUFajl(vozila.stream()
							.filter(v -> v instanceof Gradski || v instanceof BezVagona)
							.collect(Collectors.toList()), "sa baterijom.txt");
		upisUFajl(vozila.stream()
							.filter(v -> !(v instanceof Gradski) || !(v instanceof BezVagona))
							.collect(Collectors.toList()), "bez baterije.txt");
		System.out.println("Pool izvrsen!");
	}
	
	private void dodajPutnike() throws BrojnoStanjeException {
		List<Putnik> temp = vozila.get(idVozila).putnici;
		if(temp.size() + brojPutnika > 50)
			throw new BrojnoStanjeException(true);
		while(putnici.size() - brojPutnika < 100)
			generisi();
		for(int i = 0; i < brojPutnika; i++) 
			temp.add(putnici.removeFirst());
	}
	private void izbaciPutnike() throws BrojnoStanjeException {
		List<Putnik> temp = vozila.get(idVozila).putnici;
		if(temp.size() - brojPutnika < 25)
			throw new BrojnoStanjeException(false);
		for(int i = 0; i < brojPutnika; i++) 
			putnici.add(temp.removeFirst());
	}
	private void pokreniVozila() {
		for(Vozilo v : vozila) 
			v.start();
	}
	private void generisi() {
		int dodatak = rand.nextInt(10);
		for(int i = 0; i < dodatak; i++)
			putnici.add(new Putnik());
	}
	private void upisUFajl(List<Vozilo> vozila, String imeFajla) {
		try {
			FileWriter fw = new FileWriter(imeFajla, true);
			PrintWriter pw = new PrintWriter(fw);
			for(Vozilo v : vozila) {
				StringBuilder sb = new StringBuilder();
				pw.println(v.getClass().getSimpleName() + ": \n");
				//sb.append(instanceof v + ": \n");
				for(Putnik p : v.putnici)
					pw.println(p);
					//sb.append(p + "\n");
			}
			pw.close();
			fw.close();
		} catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
}