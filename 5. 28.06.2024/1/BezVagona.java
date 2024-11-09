public class BezVagona extends Tramvaj {
	public Baterija baterija = new Baterija();
	
	@Override
	public String ulaz() {
		return String.format("U tramvaj sa vagonima [%s%%] uslo je putnika: ", baterija);
	}
	@Override
	public String izlaz() {
		return "Iz tramvaja sa vagonima izaslo je putnika: ";
	}
}