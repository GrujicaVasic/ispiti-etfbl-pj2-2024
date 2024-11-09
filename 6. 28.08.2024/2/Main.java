import java.io.*;

public class Main {
	int obrisaniFajlovi = 0;
	String match;
	
	public static void main(String[] args) {
		Main m = new Main();
		File root = new File(args[0]);
		m.match = args[1];
		m.pretraga(root);
		System.out.printf("Obrisano je %d fajlova", m.obrisaniFajlovi);
	}
	
	public void pretraga(File root) {
		if(root.isDirectory()) {
			File[] files = root.listFiles();
			for(File f : files)
				pretraga(f);
		}
		else if(root.isFile() && obrisatiFajl(root)) {
			System.out.println("OBRISAN: " + root.getName());
			root.delete();
			obrisaniFajlovi++;
		}
	}
	
	private boolean obrisatiFajl(File file) {		
		String naslov = file.getName();
		naslov = fajlBezEkstenzije(naslov).toLowerCase();
		if(naslov.contains(match.toLowerCase())) 
			return true;
		return false;
	}
	
	private String fajlBezEkstenzije(String imeFajla) {
		return imeFajla.substring(0, imeFajla.lastIndexOf('.'));
	}
}