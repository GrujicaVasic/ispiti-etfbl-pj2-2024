public class Gradski extends Autobus{
	public Baterija baterija = new Baterija();
	
	@Override
	public String ulaz() {
		return String.format("U gradski autobus [%s%%] je uslo putnika: ", baterija);
	}
	@Override
	public String izlaz() {
		return "Iz gradskog autobusa je izaslo putnika: ";
	}
}