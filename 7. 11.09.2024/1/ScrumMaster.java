public class ScrumMaster extends Clan implements GenerisanjeZadataka {
	public ScrumMaster(String ime, String prezime, int godineRada) {
		super(ime, prezime, godineRada);
	}
	
	@Override
	public void run() {
		while(!projekat.jeKraj) {
			try {
				synchronized(this) {
					wait();
					projekat.dodajZadatke(generisi);
					wait();
					//.txt
				}
			} catch(InterruptedException e) { }
		}
	}
}