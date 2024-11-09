import java.nio.file.*;
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
	HashMap<String, Integer> fajlovi = new HashMap<>();
	
	static int duzinaTrazenihRijeci;
	static String fajlZaUpis;
	
	public static void main(String[] args) {
		duzinaTrazenihRijeci = Integer.valueOf(args[3]);
		fajlZaUpis = "rijeci_duzine_" + duzinaTrazenihRijeci + ".txt";
		
		Main m = new Main();
		m.ucitavanjeDirektorijuma(new File(args[1]));
		
		m.fajlovi.entrySet().stream().forEach(entry -> System.out.println(String.format("%s = %d", entry.getKey(), entry.getValue())));
		
		int suma = 0;
		int suma1 = 0;
		for(Integer i : m.fajlovi.values())
			suma += i;
		System.out.printf("%nUkupno rijeci duzine %d: %d%n", duzinaTrazenihRijeci, suma);
		/*System.out.printf("%nUkupno rijec duzine %d: %d%n", duzinaTrazenihRijeci, 
				m.fajlovi.entrySet()
						.stream().flatMapToInt(entry -> entry.getValue()).sum());*/
	}
	
	void ucitavanjeDirektorijuma(File root) {
		List<File> subDir = new LinkedList<>();
		for(File file : root.listFiles()) {
			if(file.isDirectory())
				subDir.add(file);
			else if(file.isFile()) {
				pretragaFajla(file);
			}
		}
		for(File file : subDir)
			ucitavanjeDirektorijuma(file);
	}
	
	void pretragaFajla(File fajl) {
		try {
			FileWriter fw = new FileWriter(fajlZaUpis, true);
			PrintWriter pw = new PrintWriter(fw);
			int brojac = 0;
			List<String> linije = Files.readAllLines(fajl.toPath());
			for(String l : linije) {
				String[] temp =  l.split(" ");
				for(String l1 : temp) {
					if(l1.endsWith(".") || l1.endsWith(","))
						l1 = l1.substring(0, l1.length() - 2);
					if(duzinaTrazenihRijeci == l1.length() && l1.matches("\\w+")) {
						brojac++;
						pw.println(l1);
					}
				}
			}
			if(brojac > 0)
				fajlovi.put(fajl.getAbsolutePath(), brojac);
			pw.close();
			fw.close();
		} catch(IOException e) { 
			System.err.println(e.getMessage());
		}
	}
}