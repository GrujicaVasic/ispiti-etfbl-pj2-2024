public class BrojnoStanjeException extends Exception {
	public BrojnoStanjeException(boolean jePrekoKapaciteta) {
		if(jePrekoKapaciteta)
			System.err.println("POKUSAJ ULAZA BROJA PUTNIKA KOJI JE PREKO KAPACITETA VOZILA!");
		else 
			System.err.println("POKUSAJ IZLAZA BROJA PUTNIKA KOJI JE ISPOD MINIMUMA!");
	}
}