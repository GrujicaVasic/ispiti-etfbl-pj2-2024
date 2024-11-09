public class StekPrazanException extends Exception {
	public StekPrazanException() {
		super("Stek je prazan, nije moguce uklanjanje.");
	}
}