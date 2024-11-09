public class NepostojecaRijecException extends Exception {
    public NepostojecaRijecException(int pozicija) {
        super(String.format("Na trazenoj poziciji %d ne postoji rijec!", pozicija));
    }
}