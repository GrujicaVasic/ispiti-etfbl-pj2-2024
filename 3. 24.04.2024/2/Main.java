import java.io.File;

public class Main {
	
	//Nece radit' ako se budu brisali fajlovi (ja mislim)
    public static void main(String[] args) {
        Main m = new Main();
        m.kreirajDirektorijume(Integer.parseInt(args[0]), args[1]);
		System.out.println("Program pokrenut");
    }

    private void kreirajDirektorijume(int brojDirektorijuma, String putanja) {
        for(int i = 1; i <= brojDirektorijuma; i++) {
            String finalPath = putanja + File.separator + i;
            File f = new File(finalPath);
            f.mkdir();
            new Watcher(f).start();
        }
    }
}
