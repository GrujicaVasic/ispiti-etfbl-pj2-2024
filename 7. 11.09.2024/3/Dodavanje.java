public class Dodavanje extends Thread {
	Stek s;
	boolean jeKraj;
	
	public Dodavanje(Stek s) {
		this.s = s;
		jeKraj = false;
	}
	
	@Override
	public void run() {
		while(!jeKraj) {
			try {
				Stek.Knjiga knjiga = s.new Knjiga();
				s.dodaj(knjiga);
				System.out.println("Dodata knjiga " + knjiga.naslov);
				sleep(250);
			} catch(InterruptedException e) {
				
			}
		}
	}
}