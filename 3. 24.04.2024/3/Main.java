import java.io.*;
import java.util.*;

public class Main {
	static int brojFajlova = 0;
	static int brojSkrivenih = 0;
	
	public static void main(String[] args) {
		String path = "";
		Scanner scan = new Scanner(System.in);
		File root;
		do {
			System.out.print("Unesi putanju: ");
			path = scan.nextLine();
			root = new File(path);
		} while(!root.exists());
		
		//String rootS = "C:\\Users\\Strat\\Desktop\\Rokovi\\3. 24.04.2024\\3";
		//File root = new File(rootS);
		
		//System.out.println("PROGRAM POCINJE:\n");
		
		rekur(root);
		
		System.out.println("Ukupno fajlova " + brojFajlova);
		upisiUFajlString(String.format("Ukupno fajlova " + brojFajlova));
		
		System.out.println("Skrivenih fajlova " + brojSkrivenih);
		upisiUFajlString(String.format("Skrivenih fajlova " + brojSkrivenih));
		
		double procenat = (100.00 * (double)brojSkrivenih) / (double) brojFajlova;
		System.out.println(String.format("Procenat skrivenih fajlova %.2f%%", procenat));
		upisiUFajlString(String.format("Procenat skrivenih fajlova %.2f%%", procenat));
		
	}
	
	public static void rekur(File root) {
		System.out.print(root);
		if(root.isDirectory())
				System.out.println(" (direktorijum)");
			if(root.isFile()) {
				brojFajlova++;
				System.out.println(" (fajl)");
			}
		if(root.isDirectory()){
			File[] innerFiles = root.listFiles();
			for(File f : innerFiles) 
				rekur(f);
		}
		if(root.isHidden()) {
			upisiUFajl(root);
			brojSkrivenih++;
		}
	}
	
	public static void upisiUFajl(File hidden) {
		try {
			FileWriter fw = new FileWriter("skriveni.txt", true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(hidden.getAbsolutePath());
			pw.close();
			fw.close();
		} catch(IOException e) {}
	}
	public static void upisiUFajlString(String upis) {
		try {
			FileWriter fw = new FileWriter("skriveni.txt", true);
			PrintWriter pw = new PrintWriter(fw);
			pw.println(upis);
			pw.close();
			fw.close();
		} catch(IOException e) {}
	}
}