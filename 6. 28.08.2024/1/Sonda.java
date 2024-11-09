public class Sonda extends Letjelica {
	public Laseri laseri;
	public RaketnaNeutralizacija neutral;
	
	public Sonda(int id) {
		super(id);
		laseri = new Laseri();
		neutral = new RaketnaNeutralizacija();
	}
	
	public void kretanje() {
		
	}
}