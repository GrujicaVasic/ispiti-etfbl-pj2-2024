public class Uklanjanje extends Thread {
	Stek s;
	boolean jeKraj;
	
	public Uklanjanje(Stek s) {
		this.s = s;
		jeKraj = false;
	}
	
	@Override
	public void run() {
		while(!jeKraj) {
			try {
				try {
					Stek.Knjiga knjiga = s.ukloni();
					System.out.println("Uklonjena knjiga " + knjiga.naslov);
				} catch(StekPrazanException e) {
					System.err.println(e);
				}
				sleep(400);
			} catch(InterruptedException e) {
			
			}
		}
	}
}