public class Brod extends Letjelica{
	public Rakete rakete;
	public Laseri laseri;
	public Stit stit;
	
	public Brod(int id) {
		super(id);
		rakete = new Rakete();
		laseri = new Laseri();
		stit = new Stit();
	}
	
	public void kretanje() {
		
	}
}