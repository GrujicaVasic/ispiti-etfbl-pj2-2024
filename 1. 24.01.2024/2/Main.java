import java.io.*;
import java.nio.file.*;
import java.net.URI;
import java.util.*;

public class Main {
	public static String MAIN_ROOT;
	public static String SUB_FOLDER;
	public static HashSet<String> subFolderi = new HashSet<>();
	
	public static void main(String[] args) {
		long pocetak = System.currentTimeMillis();
		MAIN_ROOT = args[0];
		SUB_FOLDER = MAIN_ROOT + File.separator + args[1];
		kreirajFolder(SUB_FOLDER);
		
		rekurzija(new File(MAIN_ROOT));
		
		for(String s : subFolderi)
			ispis(s);
		
		long kraj = System.currentTimeMillis();
		double trajanje = (kraj - pocetak) / 1000.00;
		System.out.printf("%n%nTrajanje programa je %.2f [s]", trajanje);
	}
	
	public static void rekurzija(File root) {
		if(root.isDirectory()) {
			File[] files = root.listFiles();
			for(File f : files)
				rekurzija(f);
		}
		if(root.isFile()) {
			String format = ocitajFormat(root);
			String dest = SUB_FOLDER + File.separator + format;
			kreirajFolder(dest);
			try {
				Files.copy(Paths.get(root.toURI()), new FileOutputStream(dest + File.separator + root.getName()));
			} catch(Exception e) { System.out.println(e);}
		}
	}
	private static void ispis(String tipskiFolder) {
		File folder = new File(tipskiFolder);
		int brojFajlova = 0;
		long velicinaFoldera = 0L;
		File[] files = folder.listFiles();
		for(File f : files) {
			if(f.isFile()) {
				brojFajlova++; 
				try {
					velicinaFoldera += Files.size(Paths.get(f.toURI()));
				} catch(IOException e) {System.out.println(e);}
			}
		}
		System.out.printf("%nTipski folder %s ima %d fajlova i velicinu %d [B]", tipskiFolder, brojFajlova, velicinaFoldera);
	}
	
	private static String ocitajFormat(File file) {
		String s = file.getName();
		String[] temp = s.split("\\.");
		return temp[temp.length - 1];
	}
	private static void kreirajFolder(String path) {
		File subD = new File(path);
		subD.mkdir();
		subFolderi.add(path);
	}
}