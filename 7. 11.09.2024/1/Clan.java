public abstract class Clan extends Thread {
	public Projekat projekat;
	public String ime, prezime;
	public int godine;
	
	public Clan(String ime, String prezime, int godineRada) {
		this.ime = ime;
		this.prezime = prezime;
		godine = godineRada;
	}
	
	@Override
	public String toString() {
		return String.format("%s, %s, %d", ime, prezime, godine);
	}
}