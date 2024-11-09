public class Stanice extends Letjelica {
	public Rakete rakete;
	public Laseri laseri;
	public Stit stit;
	public RaketnaNeutralizacija neutral;
	
	public Stanice(int id) {
		super(id);
		rakete = new Rakete();
		laseri = new Laseri();
		stit = new Stit();
		neutral = new RaketnaNeutralizacija();
	}
	
	public void kretanje() {
		
	}
}