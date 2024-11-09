package vozila.izuzeci;

public class ManjakPutnikaException extends Exception {
    public ManjakPutnikaException(String vrstaVozila) {
        super(String.format("U VOZILU, %s,NEMA DOVOLJNO PUTNIKA ZA TRAZENI IZLAZAK!", vrstaVozila.toUpperCase()));
    }
}
