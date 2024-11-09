public class ProductOwner extends Clan implements GenerisanjeZadataka {
	public String opis;
	
	public ProductOwner(String ime, String prezime, int godineRada, String opis) {
		super(ime, prezime, godineRada);
		this.opis = opis;
	}
	
	@Override
	public void run() {
		while(!projekat.jeKraj) {
			try {
				synchronized(this) {
					wait();
					projekat.dodajZadatke(generisi);
					wait();
				}
			} catch(InterruptedException e) { }
		}
	}
}