public class SaVagonima extends Tramvaj {
	
	@Override
	public String ulaz() {
		return "U tramvaj sa vagonima uslo je putnika: ";
	}
	@Override
	public String izlaz() {
		return "Iz tramvaja sa vagonima izaslo je putnika: ";
	}
	@Override
	protected int brojPutnika() {
		return rand.nextInt(5) + 1;
	}
}