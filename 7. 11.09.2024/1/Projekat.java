public class Projekat extends Thread {
	public PriorityBlockingQueue<Zadatak> backlog;
	public Tim tim = new Tim();
	
	public boolean jeKraj = false;
	
	private int brojSprintova = 0;
	
	@Override
	public void run() {
		while(brojSprintova < 3) {
			backlog = new PriorityBlockingQueue<>(10, (z1, z2) -> z1.prioritet - z2.prioritet);
			pocetakSprinta();
		}
	}
	
	public void dodajZadatake(PriorityBlockingQueue<Zadatak> zadaci) {
		for(Zadatak z : zadaci) 
			backlog.add(z);
	}
	
	private void pocetakSprinta() {
		synchronized(tim.po) {
			tim.po.notify();
		}
		synchronized(tim.sm) {
			tim.sm.notify();
		}
		try {
			synchronized(tim.po) {
				tim.po.wait();
			}
		} catch(InterruptedException e) { }
	}
}