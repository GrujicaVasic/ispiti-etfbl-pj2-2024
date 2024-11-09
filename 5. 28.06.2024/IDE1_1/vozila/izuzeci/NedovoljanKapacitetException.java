package vozila.izuzeci;

public class NedovoljanKapacitetException extends Exception {
    public NedovoljanKapacitetException(String vrstaVozila) {
        super(String.format("TRAZENI BROJ PUTNIKA ZA ULZAZK U %s PREMASUJE KAPACITET VOZILA!", vrstaVozila.toUpperCase()));
    }
}
